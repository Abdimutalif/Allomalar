package com.mac.allomalar.ui.activities


import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mac.allomalar.databinding.ActivityMapBinding
import com.mac.allomalar.db.database.AppDatabase
import com.mac.allomalar.utils.DownloadingService
import com.mac.allomalar.utils.NetworkStateChangeReceiver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MapActivity : AppCompatActivity(), NetworkStateChangeReceiver.ConnectivityReceiverListener {
    private lateinit var binding: ActivityMapBinding

    @Inject
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        registerReceiver(
            NetworkStateChangeReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        NetworkStateChangeReceiver.connectivityReceiverListener = this

        binding.nextButton.setOnClickListener {
            startActivity(Intent(this, AllomalarActivity::class.java))
        }
    }

    private fun startServiceFunction() {
        Intent(this, DownloadingService::class.java).also {
            startService(it)
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
      //      startServiceFunction()
        }
    }
}