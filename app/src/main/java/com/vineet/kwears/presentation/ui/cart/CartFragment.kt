package com.vineet.kwears.presentation.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.withTransaction
import coil.compose.AsyncImage
import com.vineet.kwears.data.database.dataentity.AddToCart
import com.vineet.kwears.domain.model.CartProducts
import com.vineet.kwears.databinding.FragmentCartBinding
import com.vineet.kwears.presentation.ui.checkout.Checkout
import kotlinx.coroutines.launch

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private val viewModel by viewModels<CartViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.composeView.apply{
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "cart"){
                    composable("cart"){Cart(navController)}
                    composable("checkout"){Checkout()}
                }
            }
        }
        return view
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun Cart(navController: NavController = rememberNavController()){
        
        val addedProducts = viewModel.cartProduct.getCartAddedProducts().observeAsState().value
        if(addedProducts?.isEmpty() == true){
            MaterialTheme{
                Text(
                    text = "no products added",
                    color=Color.White
                )
            }
        }
        MaterialTheme{
           Column {
                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (addedProducts?.isNotEmpty() == true) {
                        items(addedProducts.size) { index ->
                            Row {
                                CartProductDescription(addedProducts, index)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Divider(color = Color.DarkGray)
                        }
                    }
                }
               Box{
                   Button(onClick = {
                        navController.navigate("checkout")
                   }) {
                       Text(text = "Go To Checkout")
                   }
               }
            }

        }
    }

    @Composable
    fun CartProductDescription(cartProducts:List<CartProducts>, index:Int){
        val eachCartProductCount =
            viewModel
                .cartProduct
                .getEachCartProductCount(cartProducts[index].productId)
                .observeAsState()
                .value

        AsyncImage(
            model = cartProducts[index].productImages[0].src,
            modifier= Modifier
                .height(100.dp)
                .width(100.dp),
            contentDescription = cartProducts[index].productName
        )
        Spacer(modifier = Modifier.width(90.dp))

        Column{
            Text(
                text = cartProducts[index].productName,
                color = Color.White
            )
            Row{
                CartProductQuantityControls(cartProducts,index,eachCartProductCount)
            }

        }
    }
    @Composable
    fun CartProductQuantityControls(cartProducts: List<CartProducts>, index:Int, eachCartProductCount:AddToCart?){
        Button(
            onClick = {
                lifecycleScope.launch{
                    viewModel.database.withTransaction {
                        viewModel.cartProduct.insertOrUpdateCart(
                            cartProducts[index].productId
                        )
                    }
                }
            },
            Modifier.padding(10.dp)
        ) {
            Text(text="+")
        }
        Box {
            Text(
                text = eachCartProductCount?.count.toString(),
                color = Color.White,
                modifier= Modifier.align(Alignment.Center)
            )
        }
        Button(
            onClick = {
                lifecycleScope.launch{
                    viewModel.database.withTransaction {
                        if(eachCartProductCount?.count!! < 2){
                            viewModel.cartProduct.deleteCartProduct(cartProducts[index].productId)
                        }
                        viewModel.cartProduct.decrementCartProduct(cartProducts[index].productId)
                    }
                }
            },
            Modifier.padding(10.dp)
        ) {
            Text(text="-")
        }
    }
}
