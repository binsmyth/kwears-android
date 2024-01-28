package com.vineet.kwears.presentation.ui.checkout

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.vineet.kwears.data.credential
import com.vineet.kwears.data.database.AppDatabase
import com.vineet.kwears.data.network.Api
import com.vineet.kwears.data.network.dto.allshippingmethodsfromzonedto.AllShippingMethodsFromZoneDto
import com.vineet.kwears.data.network.dto.createorderresponsedto.CreateOrderResponseDto
import com.vineet.kwears.data.network.dto.orderdto.Billing
import com.vineet.kwears.data.network.dto.orderdto.OrderDto
import com.vineet.kwears.data.network.dto.orderdto.Shipping
import com.vineet.kwears.data.network.dto.orderdto.ShippingLine
import com.vineet.kwears.data.network.dto.paymentgatewaysdto.PaymentGatewaysDto
import com.vineet.kwears.data.network.dto.shippingmethoddetailsfromzonedto.ShippingMethodDetailsFromZoneDto
import com.vineet.kwears.data.network.dto.shippingzonesdto.ShippingZonesDto
import com.vineet.kwears.domain.model.AllShippingMethodsFromZone
import com.vineet.kwears.domain.model.CreateOrderPaymentMethod
import com.vineet.kwears.domain.model.ShippingZonesCheckout
import kotlinx.coroutines.launch

class CheckoutViewModel(): ViewModel() {
    var postcode by mutableStateOf("")
    var district by mutableStateOf("")
    var firstname by mutableStateOf("")
    var lastname by mutableStateOf("")
    var address1 by mutableStateOf("")
    var address2 by mutableStateOf("")
    var city by mutableStateOf("")
    var state by mutableStateOf("")
    var country by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var selectedShippingMethodItem by mutableStateOf<AllShippingMethodsFromZone?>(null)
    var selectedPaymentMethodItem by mutableStateOf<CreateOrderPaymentMethod?>(null)
    var selectedShippingZoneItems by mutableStateOf<ShippingZonesCheckout?>(null)

    suspend fun getPaymentGatewayDetails(paymentGateways:List<PaymentGatewaysDto>):List<CreateOrderPaymentMethod>{
        return paymentGateways.map{it.toCreateOrderPaymentMethod()}
    }

    suspend fun getShippingZones(shippingZones:List<ShippingZonesDto>): List<ShippingZonesCheckout> {
        return shippingZones.map{
            it.toShippingZoneCheckout()
        }
    }

    suspend fun getAllShippingMethodsFromZone(allShippingMethodsFromZone:List<AllShippingMethodsFromZoneDto>):List<AllShippingMethodsFromZone>?{
        if (selectedShippingZoneItems == null){
            return null
        }
        return allShippingMethodsFromZone.map{
            it.toAllShippingMethodsFromZone()
        }
    }
    fun submitCheckout(context: Context):CreateOrderResponseDto?{
        val database = AppDatabase.getDatabase(context)
        val cartAddedProducts = database.addCartProductDao().getCartAddedProducts()
        var createOrderResponse:CreateOrderResponseDto? = null
        val billing = Billing(
            address1 = address1,
            address2 = address2,
            city = city,
            country = country,
            email = email,
            firstName = firstname,
            lastName = lastname,
            phone = phone,
            postcode = postcode,
            state = state
        )
        val shipping = Shipping(
            address1 = address1,
            address2 = address2,
            city = city,
            country = country,
            firstName = firstname,
            lastName = lastname,
            postcode = postcode,
            state = state
        )

        val shippingLine = ShippingLine(
            methodId = selectedShippingMethodItem?.ShippingMethodId.toString(),
            methodTitle = selectedShippingMethodItem?.ShippingTitle,
            total = selectedShippingMethodItem?.cost
        )
        selectedPaymentMethodItem?.payment_method?.let {
            selectedPaymentMethodItem?.payment_method_title?.let { it1 ->
                viewModelScope.launch{
                    cartAddedProducts.asFlow().collect{ products ->
                        val order = OrderDto(
                            billing = billing,
                            lineItems = products.map{it.toLineItems()},
                            paymentMethod = it,
                            paymentMethodTitle = it1,
                            setPaid = false,
                            shipping = shipping,
                            shippingLines = listOf(shippingLine)
                        )
                        try{
                            createOrderResponse = Api.getClient().createOrder(credential, order)
                        }
                        catch(e:Exception){
                            println(e)
                        }

                    }
                }
            }
        }

        return createOrderResponse
    }
}