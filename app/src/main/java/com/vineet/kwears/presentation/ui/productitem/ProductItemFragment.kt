package com.vineet.kwears.presentation.ui.productitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.vineet.kwears.databinding.FragmentProductitemBinding

class ProductItemFragment:Fragment() {
    private lateinit var navController: NavController
    private var _binding: FragmentProductitemBinding?=null
    private val binding get() = _binding!!

    val args: ProductItemFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductitemBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = args.name //Argument of name from product fragment
        val imageSrc = args.imageSrc //Argument of image source from product fragment
        binding.productItemName.text = name //Change the text in ui for
        Glide
            .with(binding.imageView.context)
            .load(imageSrc)
            .centerCrop()
            .into(binding.imageView)
    }
}