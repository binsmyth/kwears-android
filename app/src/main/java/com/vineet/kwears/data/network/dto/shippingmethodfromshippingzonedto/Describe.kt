package com.vineet.kwears.data.network.dto.shippingmethodfromshippingzonedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Describe(
    @Json(name = "href")
    val href: String
)