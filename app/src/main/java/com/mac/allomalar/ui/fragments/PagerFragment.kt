package com.mac.allomalar.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
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
    private val TAG = "_Pager"
    private lateinit var binding: FragmentPagerBinding
    var pos: Int = position
    private  var adapter: ScholarsAdapter = ScholarsAdapter(pos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(inflater)
        Log.d(TAG, "onCreateView: $this")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        Log.d(TAG, "onViewCreated: $this")
    }
    
    override fun onResume() {
        super.onResume()
        setAdapter()
        Log.d(TAG, "onResume: $this")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: $this")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: $this")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: $this")
    }
    private fun setAdapter() {
        binding.rv.adapter = adapter
    }
    
    companion object{
        fun getInstance(pos: Int): PagerFragment = PagerFragment(pos)
    }

}