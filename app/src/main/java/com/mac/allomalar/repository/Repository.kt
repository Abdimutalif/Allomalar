package com.mac.allomalar.repository

import com.mac.allomalar.db.daos.CenturyDao
import com.mac.allomalar.db.database.AppDatabase
import com.mac.allomalar.internet.ApiService
import com.mac.allomalar.models.*
import javax.inject.Inject


class Repository @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {
    private val centuryDao = appDatabase.centuryDao()
    private val allomaAndSubjectsDao = appDatabase.allomaAndSubjectDao()
    private val allomasDao = appDatabase.allomaDao()
    private val madrasaAndAllomasDao = appDatabase.madrasaAndAllomasDao()
    private val madrasaDao = appDatabase.madrasaDao()
    private val subjectDao = appDatabase.subjectDao()

    //Read Room
    suspend fun getAllCenturiesFromRoom() = centuryDao.getAllCenturies()
    suspend fun insertAllData(list: List<Century?>?) = centuryDao.insertAll(list)

    suspend fun getAllAllomaAndSubjectsFromRoom() = allomaAndSubjectsDao.getAllomaAndSubjects()
    suspend fun insertAllomaAndSubjectsAll(list: List<AllomaAndSubjects>) =
        allomaAndSubjectsDao.insertAllomaAndSubjectsAll(list)

    suspend fun insertAllomaAndSubjects(allomaAndSubject: AllomaAndSubjects) =
        allomaAndSubjectsDao.insertAllomaAndSubjects(allomaAndSubject)

    suspend fun getAllAllomasFromRoom() = allomasDao.getAllAllomas()
    suspend fun insertAlloma(alloma: Alloma) = allomasDao.insertAlloma(alloma)
    suspend fun insertAllomas(list: List<Alloma>) = allomasDao.insertAllomas(list)

    suspend fun getAllMadrasaAndAllomasFromRoom() = madrasaAndAllomasDao.getAllMadrasaAndAllomas()
    suspend fun getAllMadrasaAndAllomas(madrasaAndAllomas: MadrasaAndAllomas) =
        madrasaAndAllomasDao.insertMadrasaAndAllomas(madrasaAndAllomas)

    suspend fun insertMadrasaAndAllomasAll(list: List<MadrasaAndAllomas>) =
        madrasaAndAllomasDao.insertMadrasaAndAllomasAll(list)

    suspend fun getAllMadrasasFromRoom() = madrasaDao.getAllMadrasas()
    suspend fun insertMadrasas(list: List<Madrasa?>?) = madrasaDao.insertMadrasas(list)

    suspend fun getAllSubjects() = subjectDao.getSubjects()
    suspend fun insertSubject(subject: Subject) = subjectDao.insertSubject(subject)
    suspend fun insertSubjectsAll(list: List<Subject>) = subjectDao.insertSubjectsAll(list)

    //ApiService
    suspend fun getAllUsers() = apiService.getScholar()
    suspend fun getAllCenturies() = apiService.getCenturies()
    suspend fun getAllMadrasas() = apiService.getMadrasas()

}