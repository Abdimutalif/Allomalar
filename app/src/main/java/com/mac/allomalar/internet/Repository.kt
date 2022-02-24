package com.mac.allomalar.internet

class Repository(private val apiService: ApiService) {
    suspend fun getCountries() = apiService.getCountries()
}