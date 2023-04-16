package com.example.kwears.network

import com.example.kwears.data.WcResponse
import com.example.kwears.data.baseurl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api{
    @GET("/wp-json/wc/v3/products")
    suspend fun getProduct(@Query("page")page:Int,@Header("Authorization")cred:String):MutableList<WcResponse>

    companion object {
        private lateinit var data:Api
        fun buildMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }
        fun getClient(): Api {

            //A logging plugin for retrofit
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            //An interceptor for putting headers into retrofit
            val apInterceptor = Interceptor {
                val originalRequest = it.request()
                val newHttpUrl = originalRequest.url().newBuilder().build()


                val newRequest = originalRequest.newBuilder()
                    /*.headers(headers)*/
                    .url(newHttpUrl)
                    .build()

                it.proceed(newRequest)
            }

            val client = OkHttpClient().newBuilder()
                .addNetworkInterceptor(apInterceptor)
                .addNetworkInterceptor(logging)
                .build()

            data = Retrofit.Builder()
                .client(client)
                .baseUrl(baseurl)
                .addConverterFactory(MoshiConverterFactory.create(buildMoshi()))
                .build()
                .create(Api::class.java)

            return data
        }
    }
}