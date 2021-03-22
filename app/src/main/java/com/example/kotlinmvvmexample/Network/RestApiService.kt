package com.example.kotlinmvvmexample.Network

import com.example.kotlinmvvmexample.Model.UserWrapper
import retrofit2.Call
import retrofit2.http.GET


interface RestApiService {
    @get:GET("bQsLPRKKMi?indent=2")
    val userList: Call<UserWrapper?>?
}