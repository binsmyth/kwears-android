package com.vineet.kwears.data.network.repository

import com.vineet.kwears.data.database.dataentity.FakeWcResponse
import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.network.dto.productdto.ProductDto
import com.vineet.kwears.data.network.dto.shippingmethodsdto.ShippingMethodsDto

interface ProductRepository{
    suspend fun getProducts(page:Int,creds:String):MutableList<WcResponse>
    suspend fun getAllProducts(creds:String):MutableList<ProductDto>

    suspend fun getShippingMethods(creds:String):List<ShippingMethodsDto>
}