package com.example.kwears.data

import com.squareup.moshi.Json

data class WcResponse(
    @Json(name="id")
    val id:String,
    @Json(name="name")
    val name:String,
    @Json(name="images")
    val images:List<Source>
)
data class Source(
    @Json(name="src")
    val src:String
)
