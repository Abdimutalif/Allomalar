package com.mac.allomalar.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.mac.allomalar.databinding.ActivitySplashBinding
import com.mac.allomalar.models.Status
import com.mac.allomalar.utils.NetworkHelper
import com.mac.allomalar.utils.NetworkStateChangeReceiver
import com.mac.allomalar.view_models.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@SuppressLint("Registered")
@AndroidEntryPoint
open class SplashActivity : AppCompatActivity(),
    NetworkStateChangeReceiver.ConnectivityReceiverListener {
    var isFirst = true

    @Inject
    lateinit var networkHelper: NetworkHelper

    var a = 0
    private val _go = MutableLiveData<Int>()

    private var mSnackBar: Snackbar? = null
    private lateinit var binding: ActivitySplashBinding
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val viewModel: SplashViewModel by viewModels()
    private var job1 = Job()
    private var job2 = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(
            NetworkStateChangeReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    private fun showMessage(isConnected: Boolean) {


        if (!isConnected) {
            val messageToUser = "You are offline now." //TODO
            mSnackBar = Snackbar.make(
                binding.root,
                messageToUser,
                Snackbar.LENGTH_LONG
            )
            mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
            mSnackBar?.show()


        } else {
            mSnackBar?.dismiss()
            binding.connecting.text = "Connecting"
            if (isFirst) {
                startWrite()
                isFirst = false
            }
        }
    }

    private fun startWrite() {
        uiScope.launch {
            viewModel.allMadrasas.observe(this@SplashActivity) { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        var job = CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(
                                this@SplashActivity,
                                "Started Writing Madrasa",
                                Toast.LENGTH_SHORT
                            ).show()
                            viewModel.insertAllMadrasa(resource.data)
                        }
                        CoroutineScope(Dispatchers.Main).launch {
                            job.join()
                            a++
                            _go.value = a
                        }

                    }
                }

            }
        }

        uiScope.launch {
            viewModel.allCenturies.observe(this@SplashActivity) { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        var job = CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(
                                this@SplashActivity,
                                "Started Writing Centuries",
                                Toast.LENGTH_SHORT
                            ).show()
                            viewModel.insertAllCenturies(resource.data)
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            job.join()
                            a++
                            _go.value = a
                        }
                    }
                }

            }
        }
    }

//    private fun readAndToast() {
//        viewModel.allMadrasas.observe(this) {
//            when (it.status) {
//                Status.SUCCESS -> {
//                    it.data?.forEach {
//                        Toast.makeText(this, it?.name, Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//    }

    override fun onResume() {
        super.onResume()
        NetworkStateChangeReceiver.connectivityReceiverListener = this
        _go.observe(this) {
            if (it == 2) {
                var intent = Intent(this, AllomalarActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }
}