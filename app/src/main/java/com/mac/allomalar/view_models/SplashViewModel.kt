package com.mac.allomalar.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mac.allomalar.models.Century
import com.mac.allomalar.models.Madrasa
import com.mac.allomalar.models.ResourceList
import com.mac.allomalar.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _allMadrasasLiveData = MutableLiveData<ResourceList<Madrasa>>()
    val allMadrasas: LiveData<ResourceList<Madrasa>>
        get() = _allMadrasasLiveData

    private val _centuryLiveData = MutableLiveData<ResourceList<Century>>()
    val allCenturies: LiveData<ResourceList<Century>>
        get() = _centuryLiveData



    init {
        getCenturies()
        readAllMadrasa()
    }

    suspend fun insertAllCenturies(list: List<Century?>?) = repository.insertAllData(list)
    suspend fun getAllCenturies() = repository.getAllCenturiesFromRoom()
    suspend fun insertAllMadrasa(list: List<Madrasa?>?) = repository.insertMadrasas(list)
    suspend fun getAllMadrasaFromRoom() = repository.getAllMadrasasFromRoom()

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

    private fun readAllMadrasa() = viewModelScope.launch {
        _allMadrasasLiveData.postValue(ResourceList.loading(null))
        repository.getAllMadrasas().let { respond ->
            if (respond.isSuccessful) {
                _allMadrasasLiveData.postValue(ResourceList.success(respond.body()))
            } else {
                _allMadrasasLiveData.postValue(
                    ResourceList.error(
                        respond.errorBody().toString(),
                        null
                    )
                )
            }
        }
    }
}