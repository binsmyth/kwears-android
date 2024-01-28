package com.example.kwears.uitest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.vineet.kwears.data.network.dto.paymentgatewaysdto.Links
import com.vineet.kwears.data.network.dto.paymentgatewaysdto.PaymentGatewaysDto
import com.vineet.kwears.data.network.dto.paymentgatewaysdto.Settings
import com.vineet.kwears.presentation.ui.checkout.CheckoutViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import com.google.common.truth.Truth;
import com.vineet.kwears.data.network.dto.allshippingmethodsfromzonedto.AllShippingMethodsFromZoneDto
import com.vineet.kwears.data.network.dto.allshippingmethodsfromzonedto.Cost
import com.vineet.kwears.data.network.dto.shippingzonesdto.Describedby
import com.vineet.kwears.data.network.dto.shippingzonesdto.Self
import com.vineet.kwears.data.network.dto.shippingzonesdto.ShippingZonesDto
import com.vineet.kwears.domain.model.AllShippingMethodsFromZone
import com.vineet.kwears.domain.model.CreateOrderPaymentMethod
import com.vineet.kwears.domain.model.ShippingZonesCheckout

class CheckoutViewModelTest {
    private val viewModel = CheckoutViewModel()

    @Test
    fun fetch_paymentgateway_and_tranform_to_createorder(){
        val listOfPaymentGatewaysDto = listOf(PaymentGatewaysDto(
            description = "description",
            enabled = false,
            id = "id",
            links = Links(
                    collection = listOf(),
                    self = listOf()
                ),
            methodDescription = "signiferumque",
            methodSupports = listOf("method supports"),
            methodTitle = "pertinax",
            order = "wisi",
            title="title",
            settings = Settings(
                instructions = null,
                title = null
            )
        ))
        runBlocking {
            val paymentGateway = viewModel.getPaymentGatewayDetails(listOfPaymentGatewaysDto)
            //Test
            Truth.assertThat(paymentGateway).isEqualTo(listOf(CreateOrderPaymentMethod("id","title")))
        }
    }

    @Test
    fun fetch_shippingzones_for_checkout_shippingzonesdto(){
        runBlocking {
            val shippingZones = viewModel.getShippingZones(shippingZones = listOf(ShippingZonesDto(
                id = 1,
                links = com.vineet.kwears.data.network.dto.shippingzonesdto.Links(
                    collection = listOf(com.vineet.kwears.data.network.dto.shippingzonesdto.Collection(
                        href = "href"
                    )),
                    describedby = listOf(Describedby(href = "describedbyhref")),
                    self = listOf(Self(href = "selfhref"))
                ),
                name = "Vineet Man Singh",
                order = 1
            )))
            //Test
            Truth.assertThat(shippingZones).isEqualTo(listOf(ShippingZonesCheckout(
                ShippingId = 1,
                ShippingName = "Vineet Man Singh"
            )))
        }
    }

    @Test
    fun fetch_all_shippingmethodsfromzonedto_transform_to_allshippingmethodsforcheckout(){
        runBlocking {
            viewModel.selectedShippingZoneItems = ShippingZonesCheckout(
                ShippingId = 1,
                ShippingName = "Vineet Man Singh"
            )
            val shippingMethodsFromZone = viewModel.getAllShippingMethodsFromZone(allShippingMethodsFromZone = listOf(
                AllShippingMethodsFromZoneDto(
                    enabled = false,
                    instanceId = 1,
                    links = com.vineet.kwears.data.network.dto.allshippingmethodsfromzonedto.Links(
                        collection = listOf(),
                        describes = listOf(),
                        self = listOf()
                    ),
                    methodDescription = "methodDescription",
                    methodId = "methodId",
                    methodTitle = "methodTitle",
                    order = 1,
                    settings = com.vineet.kwears.data.network.dto.allshippingmethodsfromzonedto.Settings(
                        classCost91 = null,
                        classCost92 = null,
                        classCosts = null,
                        cost = Cost(
                            default = "habemus",
                            description = "mediocritatem",
                            id = "parturient",
                            label = "atomorum",
                            placeholder = "deserunt",
                            tip = "quem",
                            type = "honestatis",
                            value = "10"
                        ),
                        noClassCost = null,
                        taxStatus = null,
                        title = null,
                        type = null
                    ),
                    title = "title"
                )
            ))
            Truth.assertThat(shippingMethodsFromZone).isEqualTo(listOf(AllShippingMethodsFromZone(1,"title","10")))
        }
    }
}