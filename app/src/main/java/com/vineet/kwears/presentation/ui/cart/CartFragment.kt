package com.vineet.kwears.presentation.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.withTransaction
import coil.compose.AsyncImage
import com.vineet.kwears.data.database.dataentity.CartProducts
import com.vineet.kwears.databinding.FragmentCartBinding
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        val view = binding.root
        val viewModel by viewModels<CartViewModel>()
        var cartProducts:MutableList<CartProducts> = mutableListOf<CartProducts>()
        lifecycleScope.launch {
            viewModel.database.withTransaction{
                cartProducts = viewModel.cartProduct.getCartAddedProducts()
            }
        }
        binding.composeView.apply{
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
               MaterialTheme{
                   Cart(cartProducts)
               }
            }
        }
        return view
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun Cart(cartProducts:MutableList<CartProducts> = mutableListOf<CartProducts>()){
        MaterialTheme{
            LazyColumn{
                items(cartProducts.size){index ->
                    AsyncImage(
                        model = cartProducts[index].productImages[0].src,
                        modifier= Modifier.height(100.dp).width(100.dp),
                        contentDescription = cartProducts[index].productName
                    )
                    Text(
                        text = cartProducts[index].productId.toString(),
                        color= Color.White
                    )
                    Text(
                        text = cartProducts[index].productName.toString(),
                        color = Color.White
                    )
                    Text(
                        text = cartProducts[index].cartProductCount.toString(),
                        color = Color.White
                    )
                }
                
            }
        }
    }
}