package com.vineet.kwears.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vineet.kwears.data.database.dataentity.AddToCart
import com.vineet.kwears.data.database.dataentity.CartProducts

@Dao
interface AddToCartDao {
    @Query("SELECT COUNT(productId) FROM AddToCart")
    fun getCartProductCount(): LiveData<Int>

    @Query(
            "SELECT COUNT(*) as cartProductCount, WcResponse.id as productId, WcResponse.name as productName, WcResponse.images as productImages " +
            "FROM WcResponse,AddToCart " +
            "WHERE WcResponse.id = AddToCart.productId " +
            "GROUP BY productId"
    )
    fun getCartAddedProducts():MutableList<CartProducts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddToCartProducts(addToCart: AddToCart)

    @Query("DELETE FROM AddToCart")
    fun clearCart()
}