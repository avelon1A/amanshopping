package com.example.amanshopping.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.amanshopping.adapter.HomeViewpaggerAdapter
import com.example.amanshopping.databinding.FragmentShopingBinding
import com.example.amanshopping.fragment.catogeries.AccessoryCategory
import com.example.amanshopping.fragment.catogeries.ChairCategory
import com.example.amanshopping.fragment.catogeries.CupBoardCategory
import com.example.amanshopping.fragment.catogeries.FurnitureCategory
import com.example.amanshopping.fragment.catogeries.MainCategory
import com.example.amanshopping.fragment.catogeries.TableCategory
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopingFragment : Fragment() {
   private var _binding:FragmentShopingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopingBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryFragment = arrayListOf<Fragment>(
       MainCategory(),
            ChairCategory(),
            CupBoardCategory(),
            FurnitureCategory(),
            TableCategory(),
            AccessoryCategory()
        )
        val viewPagerAdapter = HomeViewpaggerAdapter(categoryFragment,childFragmentManager,lifecycle)
        binding.viewPagerHome.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout,binding.viewPagerHome){
            tab,positon ->
            when(positon){
                0 -> tab.text = "main"
                1 -> tab.text = "chair"
                2 -> tab.text ="cupboard"
                3 -> tab.text ="furniture"
                4 -> tab.text ="table"
                5 -> tab.text ="accessory"


            }
        }.attach()

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}