package com.vineet.kwears.data.network.dto.shippingzonesdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vineet.kwears.domain.model.ShippingZonesCheckout

@JsonClass(generateAdapter = true)
data class ShippingZonesDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "_links")
    val links: Links,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: Int
){
    fun toShippingZoneCheckout():ShippingZonesCheckout{
        return ShippingZonesCheckout(
            ShippingId = id,
            ShippingName = name
        )
    }
}