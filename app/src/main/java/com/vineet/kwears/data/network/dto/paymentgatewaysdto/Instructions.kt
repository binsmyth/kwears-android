package com.vineet.kwears.data.network.dto.paymentgatewaysdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Instructions(
    @Json(name = "default")
    val default: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "label")
    val label: String,
    @Json(name = "placeholder")
    val placeholder: String,
    @Json(name = "tip")
    val tip: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "value")
    val value: String
)