package com.vineet.kwears.data.network.dto.createorderresponsedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Taxe(
    @Json(name = "id")
    val id: Int,
    @Json(name = "subtotal")
    val subtotal: String,
    @Json(name = "total")
    val total: String
)