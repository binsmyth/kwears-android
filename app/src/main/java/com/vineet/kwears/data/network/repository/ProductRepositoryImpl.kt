package com.vineet.kwears.data.network.repository

import com.vineet.kwears.data.database.dataentity.FakeWcResponse
import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.network.Api
import com.vineet.kwears.data.network.dto.productdto.ProductDto
import com.vineet.kwears.data.network.dto.shippingmethodsdto.ShippingMethodsDto

class ProductRepositoryImpl (private val api: Api): ProductRepository {
    override suspend fun getProducts(page: Int, creds: String): MutableList<WcResponse> {
        return api.getProduct(page,creds)
    }

    override suspend fun getAllProducts(creds: String): MutableList<ProductDto> {
        return api.getAllProducts(creds)
    }

    override suspend fun getShippingMethods(creds: String): List<ShippingMethodsDto> {
        return api.getShippingMethods(creds)
    }
}