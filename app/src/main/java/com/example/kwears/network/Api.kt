package com.example.kwears.network

import com.example.kwears.data.WcResponse
import retrofit2.Call
import retrofit2.http.GET

const val productrequest = "/wp-json/wc/v3/products"
public interface Api{
    @GET(productrequest)
    fun getData(): Call<List<WcResponse>>
}