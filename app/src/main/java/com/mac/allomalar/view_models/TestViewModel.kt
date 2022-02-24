package com.mac.allomalar.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mac.allomalar.repository.Repository
import com.mac.allomalar.models.Alloma
import com.mac.allomalar.models.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var _userLiveData = MutableLiveData<Resource<Alloma>>()
    val user: LiveData<Resource<Alloma>>
        get() = _userLiveData

    init {
        getScholar()
    }

    private fun getScholar()  = viewModelScope.launch {
        _userLiveData.postValue(Resource.loading(null))

        repository.getAllUsers().let {
            if (it.isSuccessful){
                _userLiveData.postValue(Resource.success(it.body()))
            }else{
                _userLiveData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}


