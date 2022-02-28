package com.mac.allomalar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mac.allomalar.R
import com.mac.allomalar.adapters.ScientificWorksAdapter
import com.mac.allomalar.databinding.FragmentScientificWorksBinding
import com.mac.allomalar.models.Book
import com.mac.allomalar.view_models.ScientificWorksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val ARG_PARAM = "alloma_id"

@AndroidEntryPoint
class ScientificWorksFragment : Fragment() {
    private var allomaId: Int = -1
    private lateinit var binding: FragmentScientificWorksBinding
    private lateinit var adapter: ScientificWorksAdapter
    private val viewModel: ScientificWorksViewModel by viewModels()
    private val list: ArrayList<Book>  = ArrayList()
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            allomaId = it.getInt(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScientificWorksBinding.inflate(layoutInflater)
        uiScope.launch {
            getBooksFromRoom()
        }
        return binding.root
    }

    private fun getBooksFromRoom() = uiScope.launch {
        list.addAll(viewModel.getAllBooksFromRoom(allomaId))
        setAdapter()
    }

    private fun setAdapter() {
        adapter = ScientificWorksAdapter(list)
        binding.rv.adapter = adapter
    }

}