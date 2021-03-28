package com.example.kotlinmvvmexample.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kotlinmvvmexample.Database.Userdata
import com.example.kotlinmvvmexample.Model.User
import com.example.kotlinmvvmexample.Model.UserWrapper
import com.example.kotlinmvvmexample.Repository.UserRepository
import com.example.kotlinmvvmexample.Utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response


class MainViewModel(private val mainRepository: UserRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {

            emit(Resource.success(data = mainRepository.getUsers()))
            Log.e("poppers",mainRepository.getUsers().toString())
        } catch (exception: Exception) {
            Log.e("poppers",exception.message.toString())
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}