package com.vineet.kwears.data.repository

import com.vineet.kwears.data.database.dataentity.WcResponse

interface ProductRepository{
    suspend fun getProducts(page:Int,creds:String):MutableList<WcResponse>
}