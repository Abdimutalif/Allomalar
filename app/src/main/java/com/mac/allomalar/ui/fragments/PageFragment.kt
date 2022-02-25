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
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mac.allomalar.R
import com.mac.allomalar.adapters.MadrasasAdapter
import com.mac.allomalar.databinding.FragmentPagerBinding
import com.mac.allomalar.models.Century
import com.mac.allomalar.models.Madrasa
import com.mac.allomalar.models.Status
import com.mac.allomalar.view_models.HomeFragmentViewModel
import com.mac.allomalar.view_models.PagerFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class PagerFragment(century: Century) : Fragment() {
    private val TAG = "_Pager"
    private lateinit var binding: FragmentPagerBinding
    private val century = century
    private val viewModel: PagerFragmentViewModel by viewModels()
    var job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main+ job)

    private lateinit var adapter: MadrasasAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerBinding.inflate(inflater)

        uiScope.launch {
            setObserves()
        }

        setBindings()
        return binding.root
    }

    private fun setObserves() {
        viewModel.allMadrasas.observe(viewLifecycleOwner){ resource ->
            when(resource.status){
                Status.SUCCESS ->{
                    binding.rv.visibility = View.VISIBLE
                    binding.progress.visibility = View.GONE

                    var list = ArrayList<Madrasa?>()
                    resource.data?.forEach {
                        if (it?.century_id == century.id)
                            list.add(it)
                    }
                    setAdapter(list)
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING ->{
                    binding.rv.visibility = View.INVISIBLE
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun setBindings() {
        binding.tvCentury.text = century.century
        binding.tvNumberOfScholars.text = century.sum_madrasa
    }

    private fun setAdapter(list: List<Madrasa?>?) {
       adapter =  MadrasasAdapter(list!!, century, object : MadrasasAdapter.MadrasaSetOnClickListener {
            override fun onMadrasaClickListener(madrasa: Madrasa, position: Int) {
                Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_fr_home_to_madrasaFragment)
            }
        })
        binding.rv.adapter = adapter
    }



    companion object {
        fun getInstance(century: Century): PagerFragment = PagerFragment(century)
    }

}