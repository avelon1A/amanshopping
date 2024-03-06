package com.example.amanshopping.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.amanshopping.databinding.FragmentLoginBinding
import com.example.amanshopping.uitl.Resource
import com.example.amanshopping.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private var result:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.loginButton.setOnClickListener {
            val email =binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(email,password)
        }
        viewModel.result.observe(viewLifecycleOwner){
        }

    }

    private fun toast(result: String?) {
        Toast.makeText(requireContext(), result, Toast.LENGTH_SHORT)?.show()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}