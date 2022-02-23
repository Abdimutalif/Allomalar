package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.mac.allomalar.R
import com.mac.allomalar.databinding.FragmentScholar1Binding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Scholar_1Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentScholar1Binding


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
        binding = FragmentScholar1Binding.inflate(layoutInflater)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.tvIjodYonalishlari.setOnClickListener {
            navigate1()
        }

        binding.image1.setOnClickListener {
            navigate1()
        }

        binding.tvScientificWorks.setOnClickListener {
            navigate2()
        }

        binding.image2.setOnClickListener {
            navigate2()
        }

        binding.tvWorldFond.setOnClickListener {
            navigate3()
        }

        binding.image3.setOnClickListener {
            navigate3()
        }

    }

    private fun navigate1(){
        findNavController().navigate(R.id.action_scholar_1Fragment_to_scholars_2Fragment)
    }

    private fun navigate2(){
        findNavController().navigate(R.id.action_scholar_1Fragment_to_scientificWorksFragment)
    }

    private fun navigate3(){
        Toast.makeText(requireContext(), "Not Yet Implemented", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_scholar_1Fragment_to_worldFondFragment)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Scholar_1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}