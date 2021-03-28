package com.example.kotlinmvvmexample.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmexample.Network.ApiHelper
import com.example.kotlinmvvmexample.Repository.UserRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(UserRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}