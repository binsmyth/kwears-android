package com.example.kwears.data.repository

import com.example.kwears.data.WcResponse

interface ProductRepository{
    suspend fun getProducts(page:Int,creds:String):MutableList<WcResponse>
}