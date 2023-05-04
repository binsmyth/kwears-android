package com.vineet.kwears.presentation.ui.cart.composeui

//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.lifecycleScope
//import androidx.room.withTransaction
//import com.vineet.kwears.data.database.dataentity.AddToCart
//import com.vineet.kwears.domain.model.CartProducts
//import com.vineet.kwears.presentation.ui.cart.CartFragment
//import kotlinx.coroutines.launch
//
//@Composable
//fun cartProductQuantityControls(cartProducts: List<CartProducts>, index:Int, eachCartProductCount: AddToCart?){
//    Button(
//        onClick = { CartFragment().whenAddProductButtonClicked(cartProducts,index) },
//        Modifier.padding(10.dp)
//    ) {
//        Text(text="+")
//    }
//    Box(
//
//    ){
//        Text(
//            text = eachCartProductCount?.count.toString(),
//            color = Color.White,
//            modifier= Modifier.align(Alignment.Center)
//        )
//    }
//    Button(
//        onClick = {
//            lifecycleScope.launch{
//                viewModel.database.withTransaction {
//                    if(eachCartProductCount?.count!! < 2){
//                        viewModel.cartProduct.deleteCartProduct(cartProducts[index].productId)
//                    }
//                    viewModel.cartProduct.decrementCartProduct(cartProducts[index].productId)
//                }
//            }
//        },
//        Modifier.padding(10.dp)
//    ) {
//        Text(text="-")
//    }
//
//}