package com.vineet.kwears.data.network.dto.shippingmethodsdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShippingMethodsDto(
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "_links")
    val links: Links,
    @Json(name = "title")
    val title: String
)