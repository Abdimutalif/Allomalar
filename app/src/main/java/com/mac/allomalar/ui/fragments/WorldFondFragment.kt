package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mac.allomalar.adapters.WorldFondAdapter
import com.mac.allomalar.databinding.FragmentWorldFondBinding
import com.mac.allomalar.models.Book
import com.mac.allomalar.models.Science
import com.mac.allomalar.view_models.WorldFondViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private const val ARG_ALLOMA_ID = "alloma_id"
private const val ARG_ALLOMA_NAME = "alloma_name"

@AndroidEntryPoint
class WorldFondFragment : Fragment() {
    private lateinit var binding: FragmentWorldFondBinding
    private var allomaId: Int =-1
    private var allomaName:  String? = null
    private lateinit var adapter : WorldFondAdapter
    private val viewModel: WorldFondViewModel by viewModels()
    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            allomaId = it.getInt(ARG_ALLOMA_ID)
            allomaName = it.getString(ARG_ALLOMA_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorldFondBinding.inflate(layoutInflater)
        binding.toolBar.title = allomaName

        uiScope.launch {
            binding.imageOfAlloma.setImageBitmap(viewModel.getImageById(viewModel.getAllomaById(allomaId).image_url)?.image)
        }

        uiScope.launch {
           readAllScienceFromRoom()
        }
        return binding.root
    }

    private fun readAllScienceFromRoom() = uiScope.launch {
        val list = viewModel.getAllScienceFromRoom(allomaId)
        setAdapter(list)
    }

    private fun setAdapter(list: List<Science>) {
        adapter = WorldFondAdapter(list)
        binding.rvWorldFond.adapter = adapter
    }

}