package com.example.kwears.network

import android.app.Application
import com.example.kwears.data.baseurl
import com.example.kwears.data.password
import com.example.kwears.data.username
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ConnectApi : Application() {
    companion object {
        private lateinit var retrofit: Retrofit

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getClient(): Retrofit {

            //A logging plugin for retrofit
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.HEADERS

            //Creating a basic credentials for woocommerce api
            val credential = Credentials.basic(username, password)

            //An interceptor for putting headers into retrofit
            val apInterceptor = Interceptor {
                val originalRequest = it.request()
                val newHttpUrl = originalRequest.url().newBuilder().build()

                val headers = originalRequest
                    .headers()
                    .newBuilder()
                    .add("Authorization",credential)
                    .build()

                val newRequest = originalRequest.newBuilder()
                    .headers(headers)
                    .url(newHttpUrl)
                    .build()

                it.proceed(newRequest)
            }

            val client = OkHttpClient().newBuilder()
                .addNetworkInterceptor(apInterceptor)
                .addNetworkInterceptor(logging)
                .build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseurl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            return retrofit
        }
    }

}