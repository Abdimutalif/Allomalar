package com.mac.allomalar.repository

import com.mac.allomalar.db.database.AppDatabase
import com.mac.allomalar.internet.ApiService
import javax.inject.Inject


class Repository @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {

    suspend fun getAllUsers() = apiService.getScholar()
    fun getAllCenturies() = appDatabase.centuryDao().getAllCenturies()

    fun getAllAllomas()=appDatabase.allomasDao().getAllAllomas()

    fun getAlloma()=appDatabase.allomaDao().getAlloma()

    fun getMadrasas()=appDatabase.madrasasDao().getAllMadrasas()
}