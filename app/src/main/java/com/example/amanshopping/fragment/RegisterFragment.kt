package com.example.amanshopping.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.amanshopping.databinding.FragmentRegisterBinding
import com.example.amanshopping.uitl.RegisterValidation
import com.example.amanshopping.uitl.Resource
import com.example.amanshopping.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.createUser(username, email, password)
        }

        lifecycleScope.launch {
            viewModel.response.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        Log.d("test", "loading")
                    }
                    is Resource.Success -> {
                        Log.d("test", resource.data.toString())
                    }
                    is Resource.Error -> {
                        Log.d("test", resource.message.toString())
                    }

                    else -> {

                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.validation.collect(){
                if(it.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.emailEditText.apply {
                            requestFocus()
                            error = it.email.message
                        }
                    }
                }
                if(it.password is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.passwordEditText.apply {
                            requestFocus()
                            error = it.password.message
                        }
                    }
                }

            }
        }
    }

    private fun makeToast(s: Resource<FirebaseUser>) {
        Toast.makeText(requireContext(), s.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
