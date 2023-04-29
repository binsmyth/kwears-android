package com.vineet.kwears.data.database.dataentity

data class CartProducts(
    val cartProductCount:Int,
    val productId:Int,
    val productName:String,
    val productImages:List<Source>
)
