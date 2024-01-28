package com.vineet.kwears.presentation.ui.product

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vineet.kwears.R
import com.vineet.kwears.data.database.dataentity.AddToCart
import com.vineet.kwears.data.database.dataentity.WcResponse

//import com.vineet.kwears.presentation.ui.product.ProductFragmentDirections


class ProductRecyclerViewAdapter(private val clickListener:OnClickListener, diffCallback:DiffUtil.ItemCallback<WcResponse>) :
    PagingDataAdapter<WcResponse, ProductRecyclerViewAdapter.ProductViewHolder>(
        ProductDiffCallback
    ){
        //An inner class for viewholder: Viewholder is used to improve performance where ListView Lacks
        //We do the findViewById here and bind it to data fetched
        inner class ProductViewHolder(view:View):RecyclerView.ViewHolder(view){
            //itemView is inherited field from super class of viewholder
            private var imageView : ImageView = itemView.findViewById(R.id.productImageView)
            private var productTextView : TextView = itemView.findViewById(R.id.productNameTextView)
            private var priceTextView:TextView = itemView.findViewById((R.id.productSalePriceTextView))
            var addToCartButton:Button = itemView.findViewById(R.id.add_to_cart_button)

            //This is used to bind the ui to the fetched data
            fun bind(item: WcResponse){
                println("item")
                println(item)
                //bind all product ui items in recyclerview
                productTextView.text = item.name
                priceTextView.text = item.price
                if (item.images.isNotEmpty()) {
                    Glide
                        .with(imageView.context)
                        .load(item.images[0].src)
                        .centerCrop()
                        .placeholder(R.drawable.shirt)
                        .into(imageView)
                }
                else {
                    Glide
                        .with(imageView.context)
                        .load(R.drawable.shirt)
                        .into(imageView)
                }
            }

            //When user clicks on product show detail of the product
            fun implementViewProductClickListener(item: WcResponse){
                itemView.setOnClickListener{
                    val name = item.name
                    val imageSrc =
                        if (item.images.isNotEmpty()) item.images[0].src
                        else "https://placehold.co/600x400/png"
                    val action =
                        ProductFragmentDirections.actionNavigationProductToNavigationProductitem(
                            name,
                            imageSrc
                        )
                    it.findNavController().navigate(action)
                }
            }
        }
        //Used to put inflated xml View into Viewholder
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ProductViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ProductViewHolder(
                inflater.inflate(
                    R.layout.product_recycler_view_row,
                    parent,
                    false
                )
            )
        }
        // assigning values to the views we created in the recycler
        // based on the position of the recycler view
        override fun onBindViewHolder(
            holder: ProductViewHolder,
            position: Int
        ) {
            getItem(position)?.let{holder.bind(it)}
            getItem(position)?.let{holder.implementViewProductClickListener(it)}
            getItem(position)?.let{productItem ->
                val item = AddToCart(productId = productItem.id)
                holder.addToCartButton.setOnClickListener{
                    clickListener.onClick(item)
                }
            }
        }
    }

//Click Listener Class
class OnClickListener(val clickListener:(productItem:AddToCart)->Unit){
    fun onClick(productItem: AddToCart) = clickListener(productItem)
}
