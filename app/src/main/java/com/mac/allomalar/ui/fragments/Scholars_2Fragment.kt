package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mac.allomalar.R
import com.mac.allomalar.adapters.FieldsAdapter
import com.mac.allomalar.databinding.FragmentMadrasa2Binding
import com.mac.allomalar.databinding.FragmentScholars2Binding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Scholars_2Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var fieldsAdapter: FieldsAdapter
    private lateinit var binding: FragmentScholars2Binding

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
        binding = FragmentScholars2Binding.inflate(layoutInflater)
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        fieldsAdapter = FieldsAdapter(listOf("", "", "", "", "", "", ""), object : FieldsAdapter.OnFieldClick{
            override fun onClick(field: Any, position: Int) {
                findNavController().navigate(R.id.action_scholars_2Fragment_to_fieldInformationFragment)
            }
        })
        binding.rv.adapter = fieldsAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Scholars_2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}