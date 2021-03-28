package com.example.kotlinmvvmexample.Network

import com.example.kotlinmvvmexample.Model.UserWrapper
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RestApiService {

    @GET("bQsLPRKKMi?indent=2")
    suspend fun userList() : List<UserWrapper>

}