package com.vineet.kwears.data.network.dto.createorderresponsedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaDataX(
    @Json(name = "id")
    val id: Int,
    @Json(name = "key")
    val key: String,
    @Json(name = "value")
    val value: String
)