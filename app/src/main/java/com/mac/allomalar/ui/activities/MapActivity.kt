package com.mac.allomalar.ui.activities


import android.content.Intent
import android.os.Bundle
import android.text.BoringLayout.make
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.snackbar.Snackbar.make
import com.mac.allomalar.adapters.PagerAdapterMap
import com.mac.allomalar.databinding.ActivityMapBinding
import com.mac.allomalar.db.database.AppDatabase
import com.mac.allomalar.ui.fragments.PageFirstFragment
import com.mac.allomalar.ui.fragments.PageSecondFragment
import com.mac.allomalar.ui.fragments.PageThirdFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding

    @Inject
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
//        binding.buxoroCard.setOnClickListener {
//            Toast.makeText(this.applicationContext,"Buxoro bosildi",Toast.LENGTH_LONG).show()
//            startActivity(Intent(this, AllomalarActivity::class.java))
//        }
//        binding.xivaCard.setOnClickListener {
//            Toast.makeText(this.applicationContext,"Xiva bosildi",Toast.LENGTH_LONG).show()
//            startActivity(Intent(this, AllomalarActivity::class.java))
//        }
//        binding.marvCard.setOnClickListener {
//            Toast.makeText(this.applicationContext,"Marv bosildi",Toast.LENGTH_LONG).show()
//        }
//        binding.makkaCard.setOnClickListener {
//            Toast.makeText(this.applicationContext,"Makka bosildi",Toast.LENGTH_LONG).show()
//        }
//        binding.madinaCard.setOnClickListener {
//            Toast.makeText(this.applicationContext,"Madina bosildi",Toast.LENGTH_LONG).show()
//        }

        var page1 = PageFirstFragment()
        var page2 = PageSecondFragment()
        var page3 = PageThirdFragment()
        var adapter = PagerAdapterMap(page1, page2, page3, 3,  supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        binding.vpMap.adapter = adapter
    }
}