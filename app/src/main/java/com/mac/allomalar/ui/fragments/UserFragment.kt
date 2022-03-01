package com.mac.allomalar.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mac.allomalar.R
import com.mac.allomalar.adapters.UserAdapter
import com.mac.allomalar.databinding.FragmentUserBinding
import com.mac.allomalar.models.Alloma
import com.mac.allomalar.models.Image
import com.mac.allomalar.view_models.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class UserFragment : Fragment() {

    private lateinit var binding:FragmentUserBinding
    private lateinit var adapter:UserAdapter
    private var  list : ArrayList<Alloma> = ArrayList()
    private var  listSearch : ArrayList<Alloma> = ArrayList()
    private val viewModel: UserViewModel by viewModels()
    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentUserBinding.inflate(layoutInflater)
        uiScope.launch {
            readAllomasFromRoom()
        }
        return binding.root
    }

    private fun readAllomasFromRoom()  = uiScope.launch {
        list.clear()
        list.addAll(viewModel.getAllAllomasFromRoom())
        setAdapter(list)
        setClick()

    }

    private fun setClick(){
        binding.searchButton.setOnClickListener {
            search(binding.etSearchAllomaByName.text.toString())
        }

        binding.etSearchAllomaByName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
              if(s.toString().isEmpty()) {
                  setAdapter(list)
              }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    override fun onResume() {
        super.onResume()
        binding.etSearchAllomaByName.setText("")
    }

    private fun search(part: String) {
        listSearch.clear()
        list.forEach {
            if (it.name.toLowerCase().contains(part.toLowerCase())){
                listSearch.add(it)
           }
            setAdapter(listSearch)
        }
    }

    private fun setAdapter(list: List<Alloma>) {
        adapter= UserAdapter(requireContext(), list, object:UserAdapter.OnItemUserClick{

            override fun onClick(alloma: Alloma?, position: Int) {
                val bundle = Bundle()
                bundle.putInt("alloma_id", alloma?.id!!)
                findNavController().navigate(R.id.action_fr_user_to_scholar_1Fragment, bundle)
            }
        })

        binding.rvPlayer.adapter=adapter
    }

}