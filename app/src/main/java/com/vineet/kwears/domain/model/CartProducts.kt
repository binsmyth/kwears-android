package com.vineet.kwears.domain.model

import com.vineet.kwears.data.database.dataentity.Source
import com.vineet.kwears.data.network.dto.orderdto.LineItem

data class CartProducts(
    val cartProductCount:Int,
    val productId:Int,
    val productName:String,
    val productImages:List<Source>
){
    fun toLineItems():LineItem{
        //Todo: Need to implement variationid for cartproduct in database
        return LineItem(productId=productId, quantity = cartProductCount,variationId=null)
    }
}
