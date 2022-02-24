package com.mac.allomalar.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mac.allomalar.R
import com.mac.allomalar.adapters.MadrasasAdapter
import com.mac.allomalar.databinding.FragmentPagerBinding
import com.mac.allomalar.models.Century

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PagerFragment(century: Century) : Fragment() {
    private val TAG = "_Pager"
    private lateinit var binding: FragmentPagerBinding
    private var century = century

    private var adapter: MadrasasAdapter =
        MadrasasAdapter(4, object : MadrasasAdapter.MadrasaSetOnClickListener {
            override fun onMadrasaClickListener(madrasa: Any, position: Int) {
                Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_fr_home_to_madrasaFragment)

            }
        })


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

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        setAdapter()
        adapter.notifyDataSetChanged()

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

    companion object {
        fun getInstance(century: Century): PagerFragment = PagerFragment(century)
    }

}