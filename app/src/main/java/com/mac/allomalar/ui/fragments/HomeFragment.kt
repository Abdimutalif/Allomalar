package com.mac.allomalar.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import com.mac.allomalar.R
import com.mac.allomalar.adapters.PagerAdapter
import com.mac.allomalar.databinding.FragmentHomeBinding
import com.mac.allomalar.databinding.FragmentPagerBinding

class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pagerAdapter: PagerAdapter
    val list = ArrayList<PagerFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: $this")

        for (i in 0..7) {
            val fragment = PagerFragment.getInstance(i)
            Log.d(TAG, "fragment[$i] -> $fragment")
            list.add(fragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: $this")
        setAdapter("onViewCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: $this")
        //    setAdapter("onResume")
        pagerAdapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: $this")
    }

    private fun setAdapter(s: String) {
        Log.d(TAG, "setAdapter: $s")
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