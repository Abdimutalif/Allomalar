package com.mac.allomalar.view_models

import androidx.lifecycle.ViewModel
import com.mac.allomalar.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorldFondViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    suspend fun getAllScienceFromRoom(allomaId: Int) = repository.getAllScienceOfAllomaFromRoom(allomaId)
    suspend fun getAllomaById(allomaId: Int) = repository.getAllomaFromRoom(allomaId)
    suspend fun getImageById(imageUrl: String) = repository.getImageFromRoomById(imageUrl)
}