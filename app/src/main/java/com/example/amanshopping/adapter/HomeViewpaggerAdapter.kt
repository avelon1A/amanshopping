package com.example.amanshopping.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewpaggerAdapter(
    private  val Fragments:List<Fragment>,
    fm:FragmentManager,
    lifecycle: Lifecycle

):FragmentStateAdapter(fm,lifecycle) {
    override fun getItemCount(): Int {
       return Fragments.size
    }

    override fun createFragment(position: Int): Fragment {
     return Fragments[position]
    }
}