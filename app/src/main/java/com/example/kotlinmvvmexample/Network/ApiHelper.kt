package com.example.kotlinmvvmexample.Network

class ApiHelper(private val apiService: RestApiService) {

    suspend fun getUsers() = apiService.userList()
}