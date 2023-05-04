package com.vineet.kwears.presentation.ui.checkout

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.withTransaction
import com.vineet.kwears.data.database.AppDatabase
import com.vineet.kwears.domain.model.CheckoutCartProducts

fun getDatabase(context: Context) : AppDatabase{
    return AppDatabase.getDatabase(context)
}


@Preview(showSystemUi = true)
@Composable
fun Checkout(){
    val composableScope = rememberCoroutineScope()
    val context = LocalContext.current
    var addedCartProducts by remember{ mutableStateOf(listOf<CheckoutCartProducts>())}

    LaunchedEffect(Unit){
        getDatabase(context).withTransaction {
            val checkoutProducts = getDatabase(context).addCartProductDao().getCheckoutProduct()
            addedCartProducts = checkoutProducts
        }
    }
    MaterialTheme{
        Column{
            addedCartProducts.forEach{
                val productName = it.name
                val salePrice = it.sale_price
                val productCount = it.count
                val totalPrice = (it.count + it.sale_price)
                Text(text = productName, color = Color.White)
                Text(text = salePrice.toString(), color=Color.White)
                Text(text = productCount.toString(), color=Color.White)
                Text(text=("Rs $totalPrice"), color=Color.White)
            }
            Button(onClick = {  }) {
                Text(text = "Place Order")
                
            }
        }

    }
}