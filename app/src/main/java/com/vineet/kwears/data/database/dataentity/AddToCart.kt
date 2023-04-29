package com.vineet.kwears.data.database.dataentity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AddToCart")
data class AddToCart(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val productId :Int,
)