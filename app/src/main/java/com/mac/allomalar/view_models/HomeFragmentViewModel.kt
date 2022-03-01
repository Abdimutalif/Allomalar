package com.mac.allomalar.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mac.allomalar.models.Century
import com.mac.allomalar.models.ResourceList
import com.mac.allomalar.repository.Repository
import com.mac.allomalar.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: Repository,
    private val networkHelper: NetworkHelper
) : ViewModel() {


    private val _centuryLiveData = MutableLiveData<ResourceList<Century>>()
    val centuries: LiveData<ResourceList<Century>>
        get() = _centuryLiveData

    init {
        if (networkHelper.isNetworkConnected()) {
            getCenturies()
        }
    }

     suspend fun getAllCenturiesFromRoom() = repository.getAllCenturiesFromRoom()

    private fun getCenturies() = viewModelScope.launch {
        _centuryLiveData.postValue(ResourceList.loading(null))

        repository.getAllCenturies().let {
            if (it.isSuccessful) {
                _centuryLiveData.postValue(ResourceList.success(it.body()))
            } else {
                _centuryLiveData.postValue(ResourceList.error(it.errorBody().toString(), null))
            }
        }
    }

}
