
package com.example.kwears.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kwears.R
import com.example.kwears.databinding.FragmentProductBinding
import com.example.kwears.databinding.ProductRecyclerViewRowBinding

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null

    // This property is only valid between onCreateView and onDestroyView
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
        val productView: RecyclerView = binding.productRecyclerView

        //A Viewmodel list observable to see if it changes.
        productViewModel.list.observe(viewLifecycleOwner) {
            val recyclerAdapter = ProductRecyclerViewAdapter(productView.context, it)
            productView.adapter = recyclerAdapter
            productView.layoutManager = GridLayoutManager(productView.context, 2)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}