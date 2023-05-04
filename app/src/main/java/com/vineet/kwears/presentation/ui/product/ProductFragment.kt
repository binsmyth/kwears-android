
package com.vineet.kwears.presentation.ui.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.withTransaction
import com.vineet.kwears.data.database.AppDatabase
import com.vineet.kwears.data.database.dataentity.AddToCart
import com.vineet.kwears.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null

    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //To show paging products
        val viewModel by viewModels<ProductViewModel>()
        //Click Listener for Add To Cart: Adding products to cart
        val addToCartButtonListener = OnClickListener{
            //Add products to cart database
            val database = AppDatabase.getDatabase(binding.root.context)
            val addToCartProductDao = database.addCartProductDao()
            lifecycleScope.launch{
                database.withTransaction {
                    addToCartProductDao.insertOrUpdateCart(it.productId)
                }
            }

        }
        val pagingAdapter = ProductRecyclerViewAdapter(addToCartButtonListener,ProductDiffCallback)

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
}