package com.vineet.kwears.data.network.dto.productdto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attribute(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "options")
    val options: List<String>,
    @Json(name = "position")
    val position: Int,
    @Json(name = "variation")
    val variation: Boolean,
    @Json(name = "visible")
    val visible: Boolean
)