package com.vineet.kwears.data.database

import android.content.Context
import androidx.room.*
import com.vineet.kwears.data.database.dao.AddToCartDao
import com.vineet.kwears.data.database.dataentity.WcResponse
import com.vineet.kwears.data.database.dao.ProductDao
import com.vineet.kwears.data.database.dataentity.AddToCart
import com.vineet.kwears.data.database.typeconverters.ListConverter

//Database created for Woocommerce Response and Add to cart function
//schema is not exported
//automigration is implemented
@Database(
    entities=[WcResponse::class, AddToCart::class],
    version=2,
    exportSchema = false,
)

@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun productDao(): ProductDao
    abstract fun addCartProductDao():AddToCartDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?= null

        fun getDatabase (context: Context):AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}