package com.vineet.kwears.data.database.dataentity

data class FakeWcResponse(
    val id:Int,
    val title:String,
    val price:Float,
    val description:String
){
    fun toWcResponse():WcResponse{
        return WcResponse(id = id, name=title,listOf(Source("https://picsum.photos/200/300")),sale_price="",price=price.toString())
    }
}
