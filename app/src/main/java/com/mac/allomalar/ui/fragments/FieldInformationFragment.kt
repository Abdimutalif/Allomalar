package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mac.allomalar.R
import com.mac.allomalar.adapters.FieldsInformationAdapter
import com.mac.allomalar.databinding.FragmentFieldInformationBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FieldInformationFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentFieldInformationBinding
    private lateinit var adapter: FieldsInformationAdapter

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
        binding = FragmentFieldInformationBinding.inflate(layoutInflater)
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        adapter = FieldsInformationAdapter(listOf("","","","","","","",""))
        binding.rv.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FieldInformationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}