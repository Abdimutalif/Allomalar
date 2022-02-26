package com.mac.allomalar.internet

import com.mac.allomalar.models.Alloma
import com.mac.allomalar.models.Century
import com.mac.allomalar.models.Madrasa
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("2")
    suspend fun getScholar(): Response<Alloma>

    @GET("centuries")
    suspend fun getCenturies(): Response<List<Century>>

    @GET("centuries/madrasas/")
    suspend fun getMadrasas(): Response<List<Madrasa>>
}