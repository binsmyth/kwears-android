package com.vineet.kwears.data.network.dto.shippingmethoddetailsfromzonedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OptionsX(
    @Json(name = "class")
    val classX: String,
    @Json(name = "order")
    val order: String
)