package com.vineet.kwears.presentation.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vineet.kwears.R
import com.vineet.kwears.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private var _binding:FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val root:View = binding.root
        binding.textView.text = "hello"
        binding.textView.setTextColor(getResources().getColor(R.color.white))
        println("Signup")
//        binding.signUpConstraintLayout{
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            setContent{
//                MaterialTheme() {
//                   Text(text="Sign Up",color=Color.White)
//                }
//            }
//        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun SignUp(){
        Text(text="Sign Up",color= Color.White)
    }
}