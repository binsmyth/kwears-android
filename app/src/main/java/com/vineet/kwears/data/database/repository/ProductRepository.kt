package com.vineet.kwears.data.database.repository

import androidx.annotation.WorkerThread
import com.vineet.kwears.data.database.dao.AddToCartDao
import com.vineet.kwears.data.database.dao.ProductDao
import com.vineet.kwears.data.database.dataentity.WcResponse

class ProductRepository(private val productDao: ProductDao, private val addToCartDao: AddToCartDao) {
    val allProducts = productDao.getAllProducts()

    @WorkerThread
    fun insert(product:MutableList<WcResponse>?){
        productDao.insertAllProducts(product)
    }

    @WorkerThread
    fun insertOrUpdateCart(productId:Int){
        addToCartDao.insertOrUpdateCart(productId)
    }

    @WorkerThread
    fun getEachCartProductCount(productId:Int){
        addToCartDao.getEachCartProductCount(productId)
    }

    @WorkerThread
    fun getCartAddedProducts(){
        addToCartDao.getCartAddedProducts()
    }

    @WorkerThread
    fun getCheckoutProduct(){
        addToCartDao.getCheckoutProduct()
    }
}