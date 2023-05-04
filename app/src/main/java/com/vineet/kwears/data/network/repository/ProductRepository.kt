package com.vineet.kwears.data.network.repository

import com.vineet.kwears.data.database.dataentity.WcResponse

interface ProductRepository{
    suspend fun getProducts(page:Int,creds:String):MutableList<WcResponse>
    suspend fun getAllProducts(page:Int,creds:String):MutableList<ProductDto>
}