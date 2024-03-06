package com.example.amanshopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onBackPressed() {
        val currentDestination = findNavController(R.id.navigation).currentDestination
        if (currentDestination?.id == R.id.shopingFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}