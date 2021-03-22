package com.example.kotlinmvvmexample.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private var retrofit: Retrofit? = null
    val apiService: RestApiService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://www.json-generator.com/api/json/get/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(RestApiService::class.java)
        }
}