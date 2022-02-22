package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mac.allomalar.R
import com.mac.allomalar.adapters.PagerAdapter
import com.mac.allomalar.adapters.ScholarsAdapter
import com.mac.allomalar.databinding.FragmentPagerBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PagerFragment(position: Int) : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentPagerBinding
    var pos: Int = position
    private lateinit var adapter: ScholarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(inflater)

        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        adapter = ScholarsAdapter(pos)

        binding.rv.adapter = adapter
    }


}