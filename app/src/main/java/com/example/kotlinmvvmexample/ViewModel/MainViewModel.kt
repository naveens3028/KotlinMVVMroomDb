package com.example.kotlinmvvmexample.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinmvvmexample.Model.User
import com.example.kotlinmvvmexample.Repository.UserRepository


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository
    val allUsers: LiveData<List<User>?>
        get() = userRepository.getMutableLiveData()

    init {
        userRepository = UserRepository(application)
    }
}