package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import com.mac.allomalar.adapters.PagerAdapter
import com.mac.allomalar.databinding.FragmentHomeBinding
import com.mac.allomalar.models.Status
import com.mac.allomalar.view_models.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*

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
            setData()
        }
        return binding.root
    }

    private fun setData(){
        CoroutineScope(Dispatchers.Main).launch {
            val list1 = homeViewModel.getAllCenturiesFromRoom()
            list.clear()
            list1.forEach{
                val fragment = PagerFragment.getInstance(it)
                list.add(fragment)
            }
            setAdapter()
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