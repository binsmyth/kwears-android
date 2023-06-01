package com.vineet.kwears.data.network.dto.paymentgatewaysdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Settings(
    @Json(name = "instructions")
    val instructions: Instructions?,
    @Json(name = "title")
    val title: Title?
)