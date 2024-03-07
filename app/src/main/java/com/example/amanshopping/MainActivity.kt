package com.example.amanshopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NavController.OnDestinationChangedListener,
    BottomNavigationView.OnNavigationItemReselectedListener{
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private var container: ConstraintLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        navController.addOnDestinationChangedListener(this)
        bottomNavigationView?.setOnNavigationItemReselectedListener(this)

        binding()

    }
    override fun onBackPressed() {
        val currentFragment = navController.currentDestination?.id

        if (currentFragment == R.id.shopingFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
      when(destination.id){
          R.id.shopingFragment -> bottomNavigationView?.visibility  = View.VISIBLE
      }
    }

    override fun onNavigationItemReselected(item: MenuItem) {

    }
    fun binding() {
        try {
            bottomNavigationView = findViewById(R.id.bottom_navigation)
            bottomNavigationView?.setOnNavigationItemReselectedListener(this@MainActivity)
            container = findViewById(R.id.main_activity)
            FirebaseApp.initializeApp(this)
            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.navController

            navController.addOnDestinationChangedListener(this)

            bottomNavigationView?.setupWithNavController(navController)

        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("TAG", "binding: ${e.message}")
        }
    }



}