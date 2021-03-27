package com.example.kotlinmvvmexample.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinmvvmexample.Database.Userdata
import com.example.kotlinmvvmexample.Model.User
import com.example.kotlinmvvmexample.Model.UserWrapper
import com.example.kotlinmvvmexample.Repository.UserRepository
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository
    val allUsers: LiveData<UserWrapper>
        get() = userRepository.getMutableLiveData()

    init {
        userRepository = UserRepository(application)
    }
}