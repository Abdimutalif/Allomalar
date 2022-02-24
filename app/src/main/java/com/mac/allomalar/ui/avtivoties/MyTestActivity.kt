package com.mac.allomalar.ui.avtivoties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mac.allomalar.R
import com.mac.allomalar.adapters.TestAdapter
import com.mac.allomalar.databinding.ActivityMyTestBinding
import com.mac.allomalar.models.Status
import com.mac.allomalar.models.just_for_test.User
import com.mac.allomalar.view_models.TestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_my_test.*

@AndroidEntryPoint
class MyTestActivity : AppCompatActivity() {

    private val testViewModel: TestViewModel by viewModels()
    private lateinit var adapter: TestAdapter

    private lateinit var binding: ActivityMyTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = TestAdapter()
        binding.rv.adapter = adapter
        setObserves()
    }

    private fun setObserves() {
        testViewModel.user.observe(this) { user ->
            when (user.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.rv.visibility = View.VISIBLE
//                    Snackbar.make(binding.root, user.data?.name.toString(), Snackbar.LENGTH_LONG).show()
//                    it.data.let { res ->
//                        res?.data.let { it1 ->
//                            adapter.submitList(it1!!)
//                        }
//                    }

                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.rv.visibility = View.INVISIBLE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.rv.visibility = View.INVISIBLE
                    Snackbar.make(binding.root, user.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

}
