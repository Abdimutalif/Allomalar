package com.mac.allomalar.adapters

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mac.allomalar.ui.fragments.PagerFragment

class PagerAdapter(fm: FragmentManager,  behavior: Int) : FragmentPagerAdapter(fm, behavior){
    override fun getCount(): Int  = 5

    override fun getItem(position: Int): Fragment {
    return PagerFragment(position)
    }

}