package com.mac.allomalar.internet

import javax.inject.Inject


class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllUsers() = apiService.getScholar()
}