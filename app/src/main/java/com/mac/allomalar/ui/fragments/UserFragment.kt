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
import com.mac.allomalar.databinding.FragmentUserBinding
import com.mac.allomalar.models.MadrasaAndAllomas
import com.mac.allomalar.view_models.MadrasaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentUserBinding
    private lateinit var adapter:ScholarsAdapter
    private lateinit var list: ArrayList<MadrasaAndAllomas>
    private val madrasaViewModel: MadrasaViewModel by viewModels()
    var job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
        list = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentUserBinding.inflate(layoutInflater)
        uiScope.launch {
            getAllomasFromRoom()
        }
        return binding.root
    }

    private fun getAllomasFromRoom() {
        CoroutineScope(Dispatchers.Main).launch {
            list.clear()
            val allomas = madrasaViewModel.getAllMadrasaAndAllomas()
            allomas.forEach { madrasaAndAlloma ->
                //           if (madrasaName == madrasaAndAlloma.madrasa_alloma)
                list.add(madrasaAndAlloma)
            }
            setAdapter()
        }

    }

    private fun setAdapter() {
        adapter= ScholarsAdapter(list,object:ScholarsAdapter.OnItemScholarClick{
            override fun onClick(madrasaAndAllomas: MadrasaAndAllomas?, position: Int) {
                val bundle = Bundle()
                madrasaAndAllomas?.id?.let { bundle.putInt("alloma_id", it) }
                findNavController().navigate(R.id.action_madrasaFragment_to_scholar_1Fragment, bundle)
            }

        })
        binding.rvPlayer.adapter=adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}