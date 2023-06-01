package com.vineet.kwears.data.network.dto.paymentgatewaysdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vineet.kwears.domain.model.CreateOrderPaymentMethod

@JsonClass(generateAdapter = true)
data class PaymentGatewaysDto(
    @Json(name = "description")
    val description: String?,
    @Json(name = "enabled")
    val enabled: Boolean,
    @Json(name = "id")
    val id: String,
    @Json(name = "_links")
    val links: Links,
    @Json(name = "method_description")
    val methodDescription: String,
    @Json(name = "method_supports")
    val methodSupports: List<String>,
    @Json(name = "method_title")
    val methodTitle: String,
    @Json(name = "order")
    val order: String,
    @Json(name = "settings")
    val settings: Settings,
    @Json(  name = "title")
    val title: String
){
    fun toCreateOrderPaymentMethod():CreateOrderPaymentMethod{
        return CreateOrderPaymentMethod(
            payment_method = id,
            payment_method_title = title
        )
    }
}
