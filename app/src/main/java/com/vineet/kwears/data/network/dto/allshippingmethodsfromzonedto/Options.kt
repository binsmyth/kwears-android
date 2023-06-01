package com.vineet.kwears.data.network.dto.allshippingmethodsfromzonedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Options(
    @Json(name = "none")
    val none: String,
    @Json(name = "taxable")
    val taxable: String
)