package com.example.kotlinmvvmexample.Network

import com.example.kotlinmvvmexample.Model.UserWrapper
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RestApiService {

    @GET("bQsLPRKKMi?indent=2")
    suspend fun userList() : Response<UserWrapper>

    companion object {
        private var retrofit: Retrofit? = null
            operator fun invoke(): RestApiService{
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl("https://www.json-generator.com/api/json/get/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit!!.create(RestApiService::class.java)
            }
    }
}