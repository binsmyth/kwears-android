package com.vineet.kwears.data.network.dto.shippingmethodfromshippingzonedto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "collection")
    val collection: List<Collection>,
    @Json(name = "describes")
    val describes: List<Describe>,
    @Json(name = "self")
    val self: List<Self>
)