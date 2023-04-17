package com.vineet.kwears.data

import com.squareup.moshi.Json
data class WcResponse(
    @Json(name="id")
    val id:Int,
    @Json(name="name")
    val name:String,
    @Json(name="images")
    val images:List<Source>,
    @Json(name="sale_price")
    val sale_price:String
)
data class Source(
    @Json(name="src")
    val src:String
)