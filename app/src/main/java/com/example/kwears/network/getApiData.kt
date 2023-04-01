package com.example.kwears.network

import com.example.kwears.data.WcResponse
import retrofit2.Call

fun getApiData(): Call<List<WcResponse>>? {
    return ApiUtils.getAPIService()?.getData()
}