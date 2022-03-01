package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mac.allomalar.R
import com.mac.allomalar.adapters.ScholarsAdapter
import com.mac.allomalar.databinding.FragmentMadrasa2Binding
import com.mac.allomalar.models.Alloma
import com.mac.allomalar.models.MadrasaAndAllomas
import com.mac.allomalar.view_models.MadrasaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val ARG_MADRASA_NAME = "madrasa_name"
private const val ARG_MADRASA_ID = "madrasa_id"
private lateinit var binding: FragmentMadrasa2Binding

@AndroidEntryPoint
class MadrasaFragment : Fragment() {
    private var madrasaName: String? = null
    private var madrasaId: Int = -1
    private lateinit var adapter: ScholarsAdapter
    private lateinit var list: ArrayList<MadrasaAndAllomas>
    private val madrasaViewModel: MadrasaViewModel by viewModels()
    var job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            madrasaName = it.getString(ARG_MADRASA_NAME)
            madrasaId = it.getInt(ARG_MADRASA_ID)
        }
        list = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMadrasa2Binding.inflate(layoutInflater)
        uiScope.launch {
            getAllomasFromRoom()
        }

        binding.tvMadrasa.text = madrasaName

        return binding.root
    }

    private fun getAllomasFromRoom() {
        CoroutineScope(Dispatchers.Main).launch {
            list.clear()
            val allomas = madrasaViewModel.getAllMadrasaAndAllomas()
            allomas.forEach { madrasaAndAlloma ->
                if (madrasaId == madrasaAndAlloma.madrasa_alloma)
                list.add(madrasaAndAlloma)
            }
            setAdapter()
        }

    }


    private fun setAdapter() {
        adapter = ScholarsAdapter(requireContext(), list, object : ScholarsAdapter.OnItemScholarClick {
            override fun onClick(madrasaAndAllomas: MadrasaAndAllomas?, position: Int) {
             val bundle = Bundle()
                madrasaAndAllomas?.id?.let { bundle.putInt("alloma_id", it) }
                findNavController().navigate(R.id.action_madrasaFragment_to_scholar_1Fragment, bundle)
            }
        })
        binding.rvMadrasa.adapter = adapter
    }
}