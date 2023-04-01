package com.example.kwears.network

class ApiUtils {
    companion object {
        fun getAPIService(): Api?{
            return ConnectApi.getClient().create(Api::class.java)
        }
    }
}