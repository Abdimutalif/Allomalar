package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.mac.allomalar.R
import com.mac.allomalar.adapters.ScholarsAdapter
import com.mac.allomalar.databinding.FragmentMadrasa2Binding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentMadrasa2Binding

class MadrasaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
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
        binding = FragmentMadrasa2Binding.inflate(layoutInflater)

        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        adapter = ScholarsAdapter(object : ScholarsAdapter.OnItemScholarClick {
            override fun onClick(scholar: Any?, position: Int) {
                Toast.makeText(requireContext(), "Scholars", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_madrasaFragment_to_scholar_1Fragment)
            }

        })

        binding.rvMadrasa.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MadrasaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}