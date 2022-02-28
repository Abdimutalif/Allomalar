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
import com.mac.allomalar.models.Madrasa
import com.mac.allomalar.models.MadrasaAndYears
import com.mac.allomalar.models.Status
import com.mac.allomalar.utils.MyService
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
    @Inject
    lateinit var networkHelper: NetworkHelper

    @Inject
    lateinit var myService: MyService

    private var a = 0
    private var isFirst = true
    private val _go = MutableLiveData<Int>()
    private var mSnackBar: Snackbar? = null
    private lateinit var binding: ActivitySplashBinding
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(
            NetworkStateChangeReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        startService(Intent(this, MyService::class.java))

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

            if (isFirst) {
                val intent = Intent(this, AllomalarActivity::class.java)
                startActivity(intent)
                finish()
                isFirst = false
            }
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

        uiScope.async {
            viewModel.allCenturies.observe(this@SplashActivity) { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val job = CoroutineScope(Dispatchers.Main).launch {
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

        uiScope.async {
            viewModel.madrasaAndAllomasLiveData.observe(this@SplashActivity) { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        var job = CoroutineScope(Dispatchers.Main).launch {
                            viewModel.insertMadrasaAndAllomas(resource.data)
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

        uiScope.async {
            viewModel.allAllomas.observe(this@SplashActivity) { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val job = CoroutineScope(Dispatchers.Main).launch {
                            viewModel.insertAllomas(resource.data)
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
            viewModel.allSubjects.observe(this@SplashActivity) { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val job = CoroutineScope(Dispatchers.Main).launch {
                            viewModel.insertSubjects(resource.data)
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
            viewModel.allBooks.observe(this@SplashActivity) { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val job = CoroutineScope(Dispatchers.Main).launch {
                            viewModel.insertAllBooks(resource.data)
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

        uiScope.async {
            viewModel.allScience.observe(this@SplashActivity) { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val job = CoroutineScope(Dispatchers.Main).launch {
                            viewModel.insertAllSciences(resource.data)
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

//    private fun writingMadrasaYears(data: List<Madrasa?>?) {
//        var list = ArrayList<MadrasaAndYears>()
//
//        data?.forEach { it ->
//            var isAvailable = false
//            var pos = -1
//            val madrasaAndYears = MadrasaAndYears()
//            madrasaAndYears.name = it?.name
//            for (i in 0 until list.size) {
//                madrasaAndYears.name = list[i].name
//                isAvailable = true
//                pos = i
//            }
//            if (isAvailable) {
//                list[pos].years?.add(it?.century_id!!)
//            }else{
//                madrasaAndYears.name = it?.name
//                madrasaAndYears.years?.add(it?.century_id!!)
//            }
//        }
//        uiScope.launch {
//            viewModel.insertAllMadrasaAndYears(list)
//        }
//    }

    override fun onResume() {
        super.onResume()
        NetworkStateChangeReceiver.connectivityReceiverListener = this
        _go.observe(this) {
            if (a == 7) {
                val intent = Intent(this, AllomalarActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }
}