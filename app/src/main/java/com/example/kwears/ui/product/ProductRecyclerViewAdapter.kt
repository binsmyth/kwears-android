package com.example.kwears.ui.product

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kwears.R
import com.example.kwears.data.WcResponse

class ProductRecyclerViewAdapter(context:Context, productList:List<WcResponse>) :
    RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder>() {
    private var ourContext: Context
    private var ourProductList:List<WcResponse>

    init{
        ourContext = context
        ourProductList = productList
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // grabbing the views from our recycler_view_row layout file
        // Kinda like in the onCreate method
        var imageView : ImageView = itemView.findViewById(R.id.productImageView)
        var productTextView : TextView = itemView.findViewById(R.id.productNameTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        //This is where you inflate the layout (Giving a look to our rows)

        val inflater = LayoutInflater.from(ourContext)
        val view = inflater.inflate(R.layout.product_recycler_view_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // assigning values to the views we created in the recycler
        // based on the position of the recycler view
        Log.d("debug onBindViewHOlder", ourProductList.toString())
        holder.productTextView.text = ourProductList[position].name
        if (ourProductList[position].images.isNotEmpty())
            holder.imageView.load(ourProductList[position].images[0].src)
        else
            holder.imageView.load(R.drawable.baseline_broken_image_24)
    }

    override fun getItemCount(): Int {
        // the recycler view just wants to know the number of items you want to displayed
        return ourProductList.size
    }

}
