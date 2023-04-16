package com.example.kwears.data.repository

import com.example.kwears.data.WcResponse
import com.example.kwears.network.Api

class ProductRepositoryImpl (private val api:Api):ProductRepository{
    override suspend fun getProducts(page: Int, creds: String): MutableList<WcResponse> {
        return api.getProduct(page,creds)
    }
}