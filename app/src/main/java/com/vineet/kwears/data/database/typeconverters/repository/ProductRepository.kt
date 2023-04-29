package com.vineet.kwears.data.database.typeconverters.repository

import androidx.annotation.WorkerThread
import com.vineet.kwears.data.database.dao.AddToCartDao
import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.database.dao.ProductDao
import com.vineet.kwears.data.database.dataentity.AddToCart

class ProductRepository(private val productDao: ProductDao, private val addToCartDao: AddToCartDao) {
    val allProducts = productDao.getAllProducts()

    @WorkerThread
    suspend fun insert(product:MutableList<WcResponse>?){
        productDao.insertAllProducts(product)
    }

    @WorkerThread
    suspend fun insertCart(addToCart: AddToCart){
        addToCartDao.insertAddToCartProducts(addToCart)
    }
}