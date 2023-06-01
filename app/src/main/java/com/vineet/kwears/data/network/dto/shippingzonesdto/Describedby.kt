package com.vineet.kwears.data.network.dto.shippingzonesdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Describedby(
    @Json(name = "href")
    val href: String
)