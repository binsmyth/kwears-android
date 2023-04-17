package com.vineet.kwears.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vineet.kwears.R
import com.vineet.kwears.data.WcResponse

class ProductRecyclerViewAdapter(diffCallback:DiffUtil.ItemCallback<WcResponse>/*context:Context, productList: MutableList<WcResponse>?*/) :
    PagingDataAdapter<WcResponse, ProductRecyclerViewAdapter.ProductViewHolder>(
        ProductDiffCallback)/*RecyclerView.Adapter<ProductRecyclerViewAdapter.MyProductViewHolder>()*/ {

        //An inner class for viewholder: Viewholder is used to improve performance where ListView Lacks
        //We do the findViewById here and bind it to data fetched
        inner class ProductViewHolder(view:View):RecyclerView.ViewHolder(view){
            var imageView : ImageView = itemView.findViewById(R.id.productImageView)
            var productTextView : TextView = itemView.findViewById(R.id.productNameTextView)
            var salePriceTextView:TextView = itemView.findViewById((R.id.productSalePriceTextView))

            //This is used to bind the ui to the fetched data
            fun bind(item:WcResponse){
                productTextView.text = item.name
                salePriceTextView.text = item.sale_price
                if (item.images.isNotEmpty())
                        imageView.load(item.images[0].src)
                    else
                        imageView.load(R.drawable.shirt)
            }
            //When user clicks on product show detail of the product
            fun implementViewProductClickListener(item:WcResponse){
                itemView.setOnClickListener{
                    val name = item.name
                    val imageSrc =
                        if (item.images.isNotEmpty()) item.images[0].src
                        else "https://placehold.co/600x400/png"
                    val action = ProductFragmentDirections.actionNavigationProductToNavigationProductitem(name,imageSrc)
                    it.findNavController().navigate(action)
                }
            }
        }
        //Used to put inflated xml View into Viewholder
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ProductRecyclerViewAdapter.ProductViewHolder {
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
            holder: ProductRecyclerViewAdapter.ProductViewHolder,
            position: Int
        ) {
            getItem(position)?.let{holder.bind(it)}
            getItem(position)?.let{holder.implementViewProductClickListener(it)}
        }
    }


//    private var ourContext: Context
//    private lateinit var ourProductList: MutableList<WcResponse>
//
//    init{
//        ourContext = context
//        if (productList != null) {
//            ourProductList = productList
//        }
//    }
//
//    inner class MyProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        // grabbing the views from our recycler_view_row layout file
//        // Kinda like in the onCreate method
//        var imageView : ImageView = itemView.findViewById(R.id.productImageView)
//        var productTextView : TextView = itemView.findViewById(R.id.productNameTextView)
//        var salePriceTextView:TextView = itemView.findViewById((R.id.productSalePriceTextView))
//        init{
//            itemView.setOnClickListener{
//                val name = ourProductList[bindingAdapterPosition].name
//                val imageSrc:String = if (ourProductList[bindingAdapterPosition].images.isNotEmpty())
//                    ourProductList[bindingAdapterPosition].images[0].src
//                else
//                    "https://placehold.co/600x400/png"
//                val action = ProductFragmentDirections.actionNavigationProductToNavigationProductitem(name,imageSrc)
//                it.findNavController().navigate(action)
//            }
//        }
//    }

//
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): MyProductViewHolder {
//        //This is where you inflate the layout (Giving a look to our rows)
//        val inflater = LayoutInflater.from(ourContext)
//        val view = inflater.inflate(R.layout.product_recycler_view_row, parent, false)
//        return MyProductViewHolder(view)
//
//    }
//
//    override fun onBindViewHolder(holder: ProductRecyclerViewAdapter.MyProductViewHolder, position: Int) {
//        // assigning values to the views we created in the recycler
//        // based on the position of the recycler view
//        Log.d("debug onBindViewHOlder", ourProductList.toString())
//        holder.productTextView.text = ourProductList[position].name
//        if (ourProductList[position].sale_price.isNotEmpty()) {
//            holder.salePriceTextView.text = ourProductList[position].sale_price
//        } else {
//            holder.salePriceTextView.text = "N/A"
//        }
//        if (ourProductList[position].images.isNotEmpty())
//            holder.imageView.load(ourProductList[position].images[0].src)
//        else
//            holder.imageView.load(R.drawable.shirt)
//    }
//
//    override fun getItemCount(): Int {
//        // the recycler view just wants to know the number of items you want to displayed
//        return ourProductList.size
//    }
//}
