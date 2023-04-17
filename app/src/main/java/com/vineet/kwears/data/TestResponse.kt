package com.vineet.kwears.data

import com.squareup.moshi.Json

data class TestResponse(
    @Json(name="namespace")
    val namespace:String
)
