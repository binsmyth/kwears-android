package com.vineet.kwears.data.repository

import com.vineet.kwears.data.WcResponse
import com.vineet.kwears.network.Api

class ProductRepositoryImpl (private val api: Api):ProductRepository{
    override suspend fun getProducts(page: Int, creds: String): MutableList<WcResponse> {
        return api.getProduct(page,creds)
    }
}