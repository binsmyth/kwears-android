package com.vineet.kwears.data.network.dto.createorderresponsedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "collection")
    val collection: List<Collection>,
    @Json(name = "self")
    val self: List<Self>
)