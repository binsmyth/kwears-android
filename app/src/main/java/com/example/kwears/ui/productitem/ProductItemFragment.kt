package com.example.kwears.ui.productitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.example.kwears.R
import com.example.kwears.databinding.FragmentProductBinding
import com.example.kwears.databinding.FragmentProductitemBinding
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import coil.load
import com.example.kwears.databinding.ActivityMainBinding

class ProductItemFragment:Fragment() {
    private lateinit var navController: NavController
    private var _binding:FragmentProductitemBinding?=null
    private val binding get() = _binding!!

    val args:ProductItemFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductitemBinding.inflate(inflater, container, false)
        val root:View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = args.name
        val imageSrc = args.imageSrc
        binding.productItemName.text = name
        println(imageSrc)
        binding.imageView.load(imageSrc)
    }
}