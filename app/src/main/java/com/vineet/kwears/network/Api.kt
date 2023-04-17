package com.vineet.kwears.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vineet.kwears.data.TestResponse
import com.vineet.kwears.data.WcResponse
import com.vineet.kwears.data.baseurl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api{
    @GET("/wordpress//wp-json/wc/v3/products")
    suspend fun getProduct(@Query("page")page:Int,@Header("Authorization")cred:String):MutableList<WcResponse>

    @GET("/wordpress/wp-json/wc/v3")
    suspend fun testApi():List<TestResponse>

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