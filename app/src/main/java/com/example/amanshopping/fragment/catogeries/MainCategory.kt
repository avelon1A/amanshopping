package com.example.amanshopping.fragment.catogeries

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amanshopping.R
import com.example.amanshopping.adapter.SpecialProductAdapter
import com.example.amanshopping.databinding.CategoryMainBinding
import com.example.amanshopping.uitl.Resource
import com.example.amanshopping.viewmodel.MainCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MainCategory : Fragment(R.layout.category_main) {
    private lateinit var binding: CategoryMainBinding
    private lateinit var specialProductAdapter: SpecialProductAdapter
    private val viewModel by viewModels<MainCategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoryMainBinding.inflate(inflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpecialProductRv()

        lifecycleScope.launchWhenStarted {
            viewModel.specialProduct.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        showLoading()
                    }

                    is Resource.Success -> {
                           specialProductAdapter.differ.submitList(it.data)
                        Log.d("msg","${it.data}")
                        hidePrgress()
                    }

                    else -> {
                        Log.e("error","${it.message}")
                        Toast.makeText(context,it.message.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun hidePrgress() {
        binding.progressIcon.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressIcon.visibility = View.VISIBLE
    }

    private fun setupSpecialProductRv() {
        specialProductAdapter = SpecialProductAdapter()
        binding.specialRecycleView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = specialProductAdapter
        }
    }

}