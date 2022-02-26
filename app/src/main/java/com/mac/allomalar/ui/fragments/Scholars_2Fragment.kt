package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mac.allomalar.R
import com.mac.allomalar.adapters.FieldsAdapter
import com.mac.allomalar.databinding.FragmentMadrasa2Binding
import com.mac.allomalar.databinding.FragmentScholars2Binding
import com.mac.allomalar.models.Subject
import com.mac.allomalar.view_models.Scholar2ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ARG_ALLOMA_ID = "alloma_id"

@AndroidEntryPoint
class Scholars_2Fragment : Fragment() {
    private var allomID = -1
    private lateinit var fieldsAdapter: FieldsAdapter
    private lateinit var binding: FragmentScholars2Binding
    private val viewModel: Scholar2ViewModel by viewModels()
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private lateinit var list : List<Subject>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            allomID = it.getInt(ARG_ALLOMA_ID)
        }
        list = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScholars2Binding.inflate(layoutInflater)

        uiScope.launch {
        readSubjects()
        }

        return binding.root
    }

    private fun readSubjects() = uiScope.launch{
        list = viewModel.getAllSubjects(allomID)
        setAdapter(list)
    }

    private fun setAdapter(list: List<Subject>) {
        fieldsAdapter = FieldsAdapter(list, object : FieldsAdapter.OnFieldClick{
            override fun onClick(subject: Subject?, position: Int) {
                findNavController().navigate(R.id.action_scholars_2Fragment_to_fieldInformationFragment)
            }
        })
        binding.rv.adapter = fieldsAdapter
    }
}