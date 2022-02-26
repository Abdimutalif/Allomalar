package com.mac.allomalar.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mac.allomalar.R
import com.mac.allomalar.adapters.PagerAdapter
import com.mac.allomalar.databinding.FragmentHomeBinding
import com.mac.allomalar.databinding.FragmentPagerBinding
import com.mac.allomalar.models.Century
import com.mac.allomalar.models.Status
import com.mac.allomalar.view_models.AllomalarViewModel
import com.mac.allomalar.view_models.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.*
import kotlin.math.log

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"
    private val homeViewModel: HomeFragmentViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pagerAdapter: PagerAdapter
    val list = ArrayList<PagerFragment>()
    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)


        uiScope.launch {
            fun1()
        }
        return binding.root
    }

    private fun fun1(){
        CoroutineScope(Dispatchers.Main).launch {
            var list1 = homeViewModel.getAllCenturiesFromRoom()
            list1.forEach{
                val fragment = PagerFragment.getInstance(it!!)
                list.add(fragment)
            }
            setAdapter()
        }
    }
    private fun setObservers() {
        homeViewModel.centuries.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    list.clear()
                    vp.visibility = View.VISIBLE
                    progress.visibility = View.GONE
                    resource.data?.forEach {
                        val fragment = PagerFragment.getInstance(it!!)
                        list.add(fragment)
                    }

                    setAdapter()
//                    CoroutineScope(Dispatchers.Main).launch {
//                        delay(3000)
//                        homeViewModel.insertAllCenturies(resource.data)
//                        Toast.makeText(requireContext(), "I inserted", Toast.LENGTH_SHORT).show()
//                        homeViewModel.getAllCenturies().forEach {
//                            Toast.makeText(requireContext(), it.century, Toast.LENGTH_SHORT).show()
//                        }
//                    }
                }
                Status.LOADING -> {
                    vp.visibility = View.INVISIBLE
                    progress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun setAdapter() {
        val tab = binding.dotsIndicator
        val viewPager = binding.vp
        pagerAdapter = PagerAdapter(
            list,
            list.size,
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        viewPager.adapter = pagerAdapter
        tab.setViewPager(viewPager)
    }

}