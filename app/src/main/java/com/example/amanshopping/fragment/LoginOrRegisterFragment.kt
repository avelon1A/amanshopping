package com.example.amanshopping.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amanshopping.R
import com.example.amanshopping.databinding.FragmentLoginOrRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginOrRegisterFragment : Fragment() {

    private var _binding: FragmentLoginOrRegisterBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginOrRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_loginOrRegisterFragment_to_loginFragment)
        }
        binding.button2.setOnClickListener {
            findNavController().navigate(R.id.action_loginOrRegisterFragment_to_registerFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}