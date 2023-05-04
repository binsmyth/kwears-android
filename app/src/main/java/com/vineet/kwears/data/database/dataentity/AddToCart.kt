package com.vineet.kwears.data.database.dataentity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "AddToCart", indices=[Index(value=["productId"], unique = true)])
data class AddToCart(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val productId :Int,
    val count: Int=0
)