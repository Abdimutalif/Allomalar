package com.mac.allomalar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.mac.allomalar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var controller = findNavController(R.id.bottom_navigation)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            var id = it.itemId
            when (id) {
                R.id.home -> {
                    controller.navigate(R.id.homeFragment)
                }
                R.id.map -> {
                    controller.navigate(R.id.mapFragment)
                }
                R.id.user -> {
                    controller.navigate(R.id.userFragment)
                }
                R.id.settings->{
                    controller.navigate(R.id.settingsFragment)
                }
            }
            true
        }
    }
}