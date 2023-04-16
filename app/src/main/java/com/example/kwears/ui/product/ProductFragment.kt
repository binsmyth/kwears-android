
package com.example.kwears.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kwears.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null

    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
//        val viewModel by viewModels<ProductViewModel>()


//        val productViewModel =
//            ViewModelProvider(this)[ProductViewModel::class.java]
//
        _binding = FragmentProductBinding.inflate(inflater, container, false)
//
//        val productView: RecyclerView = binding.productRecyclerView
//        lifecycleScope.launch {
//            try{
//                productViewModel.getApiProduct(1,credential)
//            }
//            catch(e:Error){
//                println(e)
//            }
//
//        }
//        observeProductList(productViewModel, productView)//Observe and update recyclerview
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by viewModels<ProductViewModel>()
        val pagingAdapter = ProductRecyclerViewAdapter(ProductDiffCallback)
        val recyclerView = binding.productRecyclerView
        recyclerView.layoutManager = GridLayoutManager(binding.productRecyclerView.context, 2)
        recyclerView.adapter = pagingAdapter


        lifecycleScope.launch{
            viewModel.productPager.collectLatest{ pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    A function to observe product list from api
//    private fun observeProductList(productViewModel:ProductViewModel, productView: RecyclerView){
//        //A Viewmodel list observable to see if it changes.
//        productViewModel.list.observe(viewLifecycleOwner) {
//            val recyclerAdapter = ProductRecyclerViewAdapter(productView.context, it)
//            productView.adapter = recyclerAdapter
//            productView.layoutManager = GridLayoutManager(productView.context, 2)
//        }
//    }
}