
package com.example.kwears.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kwears.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val productViewModel =
            ViewModelProvider(this)[ProductViewModel::class.java]

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val listView: ListView = binding.productList
        val productView: RecyclerView = binding.productRecyclerView

        productViewModel.list.observe(viewLifecycleOwner) {
            val listOfProductId = it.map{
                    product ->
                        if(product.images.isNotEmpty())
                            product.images[0].src
                        else
                            "N/A"
            }
            val productAdapter = ArrayAdapter<String>(productView.context, android.R.layout.simple_list_item_1)
            listView.adapter = productAdapter
            listOfProductId.forEach{src -> productAdapter.add(src)}
            val recyclerAdapter = ProductRecyclerViewAdapter(productView.context,listOfProductId)
            productView.adapter = recyclerAdapter
//            productView.layoutManager = LinearLayoutManager(productView.context)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}