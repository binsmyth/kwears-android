package com.vineet.kwears.data.database.dataentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.Json

@Entity(tableName = "WcResponse")
@TypeConverters
data class WcResponse(
    @PrimaryKey
    @ColumnInfo(name="id")
    @Json(name="id")
    val id:Int,
    @ColumnInfo(name="name")
    @Json(name="name")
    val name:String,
    @ColumnInfo(name="images")
    @Json(name="images")
    val images:List<Source>,
    @ColumnInfo(name="sale_price")
    @Json(name="sale_price")
    val sale_price:String
)
data class Source(
    @Json(name="src")
    val src:String
)
