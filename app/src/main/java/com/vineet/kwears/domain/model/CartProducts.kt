package com.vineet.kwears.domain.model

import com.vineet.kwears.data.database.dataentity.Source

data class CartProducts(
    val cartProductCount:Int,
    val productId:Int,
    val productName:String,
    val productImages:List<Source>
)
