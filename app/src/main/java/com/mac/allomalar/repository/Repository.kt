package com.mac.allomalar.repository

import com.mac.allomalar.db.daos.CenturyDao
import com.mac.allomalar.db.database.AppDatabase
import com.mac.allomalar.internet.ApiService
import com.mac.allomalar.models.Century
import javax.inject.Inject


class Repository @Inject constructor(
    private val apiService: ApiService,
    private val centuryDao: CenturyDao
) {

    suspend fun getAllUsers() = apiService.getScholar()
    suspend fun getAllCenturies() = apiService.getCenturies()
    suspend fun getAllMadrasas() = apiService.getMadrasas()

    //Read Room
    suspend fun getAllCenturiesFromRoom() = centuryDao.getAllCenturies()
    suspend fun insertAllData(list: List<Century?>?) = centuryDao.insertAll(list)

//    fun getAllAllomas()=appDatabase.allomasDao().getAllAllomas()
//
//    fun getAlloma()=appDatabase.allomaDao().getAlloma()
//
//    fun getMadrasas()=appDatabase.madrasasDao().getAllMadrasas()
}