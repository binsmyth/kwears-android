package com.vineet.kwears.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vineet.kwears.data.database.dataentity.AddToCart
import com.vineet.kwears.domain.model.CartProducts
import com.vineet.kwears.domain.model.CheckoutCartProducts

@Dao
interface AddToCartDao {
    //Destroy whole database when app closed
    @Query("DELETE FROM AddToCart")
    fun clearCart()

    //A livedata which can be observed by cart icon to update number of products added(Usage:Menu from MainActivity)
    @Query("SELECT SUM(count) FROM AddToCart")
    fun getCartProductCount(): LiveData<Int>

    //A livedata for each product count
    @Query("SELECT * from AddToCart WHERE productId=:productId")
    fun getEachCartProductCount(productId:Int):LiveData<AddToCart>

    //Show added products in cart fragment(Usage:Cart Fragment)
    @Query(
            "SELECT COUNT(*) as cartProductCount, WcResponse.id as productId, WcResponse.name as productName, WcResponse.images as productImages " +
            "FROM WcResponse,AddToCart " +
            "WHERE WcResponse.id = AddToCart.productId " +
            "GROUP BY productId"
    )
    fun getCartAddedProducts(): LiveData<List<CartProducts>>


    //Increments number of each product added into cart(Usage: Cart Fragment)
    @Query("INSERT INTO AddToCart VALUES(null,:productId,1) ON CONFLICT(productId) DO UPDATE SET count = count + 1")
    fun insertOrUpdateCart(productId:Int)

    //Decrement number of each product added into cart(Usage:Cart Fragment)
    @Query(
        "UPDATE AddToCart "+
        "SET count = count - 1 "+
        "WHERE productId = :productId"
    )
    fun decrementCartProduct(productId:Int):Int

    @Query(
        "DELETE FROM AddToCart "+
        "WHERE productId = :productId"
    )
    fun deleteCartProduct(productId: Int)

    @Query(
        "SELECT * FROM AddToCart JOIN WcResponse " +
        "WHERE AddToCart.productId = WcResponse.id"
    )
    fun getCheckoutProduct():List<CheckoutCartProducts>
}