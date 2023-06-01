package com.vineet.kwears.data.network.dto.shippingmethodfromshippingzonedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShippingZoneFromShippingMethod(
    @Json(name = "enabled")
    val enabled: Boolean,
    @Json(name = "instance_id")
    val instanceId: Int,
    @Json(name = "_links")
    val links: Links,
    @Json(name = "method_description")
    val methodDescription: String,
    @Json(name = "method_id")
    val methodId: String,
    @Json(name = "method_title")
    val methodTitle: String,
    @Json(name = "order")
    val order: Int,
    @Json(name = "settings")
    val settings: Settings,
    @Json(name = "title")
    val title: String
)