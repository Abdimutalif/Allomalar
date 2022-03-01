package com.mac.allomalar.view_models

import androidx.lifecycle.ViewModel
import com.mac.allomalar.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Scholar2ViewModel @Inject constructor(
   private  val repository: Repository
): ViewModel(){
    suspend fun getAllSubjects(allomaId: Int) = repository.getAllSubjectsFromRoom(allomaId)
    suspend fun getAllomaById(allomaId: Int) = repository.getAllomaFromRoom(allomaId)
    suspend fun getImageById(allomaImage: String) = repository.getImageFromRoomById(allomaImage)
}