package com.vineet.kwears.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vineet.kwears.data.database.dataentity.WcResponse

@Dao
interface  ProductDao {
    @Query("Select * From WcResponse")
    fun getAllProducts():List<WcResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(products:MutableList<WcResponse>?)
}