package com.mac.allomalar.ui.activities


import android.content.Intent
import android.os.Bundle
import android.text.BoringLayout.make
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar.make
import com.mac.allomalar.databinding.ActivityMapBinding
import com.mac.allomalar.db.database.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding

    @Inject
    lateinit var db: AppDatabase
    val x=binding.horizontalScroll.getLeft()
    val y = binding.horizontalScroll.getTop()
    //binding.horizontalScroll.scrollTo(x,y)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.buxoroCard.setOnClickListener {
            Toast.makeText(this.applicationContext,"Buxoro bosildi",Toast.LENGTH_LONG).show()
            startActivity(Intent(this, AllomalarActivity::class.java))
        }
        binding.xivaCard.setOnClickListener {
            Toast.makeText(this.applicationContext,"Xiva bosildi",Toast.LENGTH_LONG).show()
            startActivity(Intent(this, AllomalarActivity::class.java))
        }
        binding.marvCard.setOnClickListener {
            Toast.makeText(this.applicationContext,"Marv bosildi",Toast.LENGTH_LONG).show()
        }
        binding.makkaCard.setOnClickListener {
            Toast.makeText(this.applicationContext,"Makka bosildi",Toast.LENGTH_LONG).show()
        }
        binding.madinaCard.setOnClickListener {
            Toast.makeText(this.applicationContext,"Madina bosildi",Toast.LENGTH_LONG).show()
        }
    }
}