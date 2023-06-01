package com.vineet.kwears.presentation.ui.checkout

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.vineet.kwears.data.credential
import com.vineet.kwears.data.database.AppDatabase
import com.vineet.kwears.data.network.Api
import com.vineet.kwears.data.network.dto.shippingmethodsdto.ShippingMethodsDto
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
    var phone by mutableStateOf("")
    var selectedShippingMethodItem by mutableStateOf<AllShippingMethodsFromZone?>(null)
    var selectedPaymentMethodItem by mutableStateOf<CreateOrderPaymentMethod?>(null)
    var selectedShippingZoneItems by mutableStateOf<ShippingZonesCheckout?>(null)

    suspend fun getPaymentGatewayDetails():List<CreateOrderPaymentMethod>{
        val paymentGateways = Api.getClient().getPaymentGateways(credential)
        return paymentGateways.map{it.toCreateOrderPaymentMethod()}
    }

    suspend fun getShippingZones(): List<ShippingZonesCheckout> {
        return Api.getClient().getShippingZones(credential).map{
            it.toShippingZoneCheckout()
        }
    }

    suspend fun getAllShippingMethodsFromZone():List<AllShippingMethodsFromZone>?{
        Log.d("getting shipping methods from zone",selectedShippingZoneItems.toString())
        if (selectedShippingZoneItems == null){
            return null
        }
        return Api.getClient().getAllShippingMethodsOfZone(credential, selectedShippingZoneItems?.ShippingId).map{
            it.toAllShippingMethodsFromZone()
        }
    }
    fun submitCheckout(context: Context){
        val database = AppDatabase.getDatabase(context)
        val cartAddedProducts = database.addCartProductDao().getCartAddedProducts()
        viewModelScope.launch{
            val shippingMethodDetails = Api.getClient().getAllShippingMethodsOfZone(credential, selectedShippingZoneItems?.ShippingId)
            println(shippingMethodDetails)
        }
        viewModelScope.launch{
            cartAddedProducts.asFlow().collect{
                println(it)
            }
        }
        println(selectedShippingMethodItem?.ShippingTitle)
        println(selectedPaymentMethodItem?.payment_method)
        println(selectedShippingZoneItems?.ShippingName)
    }
}