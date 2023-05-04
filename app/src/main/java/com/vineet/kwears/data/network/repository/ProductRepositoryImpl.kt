package com.vineet.kwears.data.network.repository

import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.network.Api

class ProductRepositoryImpl (private val api: Api): ProductRepository {
    override suspend fun getProducts(page: Int, creds: String): MutableList<WcResponse> {
        return api.getProduct(page,creds)
    }

    override suspend fun getAllProducts(page: Int, creds: String): MutableList<ProductDto> {
        return api.getAllProducts(page,creds)
    }
}