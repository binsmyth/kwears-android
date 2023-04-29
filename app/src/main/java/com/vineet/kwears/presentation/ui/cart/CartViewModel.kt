package com.vineet.kwears.presentation.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vineet.kwears.data.database.AppDatabase

class CartViewModel(Application: Application) : AndroidViewModel(Application) {
    val database = AppDatabase.getDatabase(getApplication<Application>().applicationContext)

    val cartProduct = database.addCartProductDao()
}