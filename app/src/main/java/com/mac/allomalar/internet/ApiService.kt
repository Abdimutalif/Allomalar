package com.mac.allomalar.internet

import com.mac.allomalar.models.Alloma
import com.mac.allomalar.models.just_for_test.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

//    @GET("users")
//    suspend fun getUsers(): Response<List<User>>

    @GET("2")
    suspend fun getScholar(): Response<Alloma>

}