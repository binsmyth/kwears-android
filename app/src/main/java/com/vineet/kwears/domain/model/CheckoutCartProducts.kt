package com.vineet.kwears.domain.model

data class CheckoutCartProducts(
    val id:Int,
    val productId:Int,
    val count:Int,
    val name:String,
    val sale_price:Int
)
