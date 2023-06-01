package com.vineet.kwears.data.network.dto.shippingzonesdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "collection")
    val collection: List<Collection>,
    @Json(name = "describedby")
    val describedby: List<Describedby>,
    @Json(name = "self")
    val self: List<Self>
)