package com.vineet.kwears.data.network.dto.productdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dimensions(
    @Json(name = "height")
    val height: String,
    @Json(name = "length")
    val length: String,
    @Json(name = "width")
    val width: String
)