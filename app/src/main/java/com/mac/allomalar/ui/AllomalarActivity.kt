package com.mac.allomalar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mac.allomalar.R
import com.mac.allomalar.databinding.ActivityAllomalarBinding
import kotlin.concurrent.fixedRateTimer

class AllomalarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllomalarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllomalarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setBottomNavGraph()

    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this,R.id.fragmentContainerView).navigateUp()
    }
    private fun setBottomNavGraph() {
        var navController = findNavController(R.id.fragmentContainerView)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNav.setupWithNavController(navController)
    }
}