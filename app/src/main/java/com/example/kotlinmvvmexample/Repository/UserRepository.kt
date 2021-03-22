package com.example.kotlinmvvmexample.Repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kotlinmvvmexample.Model.User
import com.example.kotlinmvvmexample.Model.UserWrapper
import com.example.kotlinmvvmexample.Network.RetrofitInstance.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository(private val application: Application) {
    private var users: ArrayList<User>? = ArrayList()
    private val mutableLiveData = MutableLiveData<List<User>?>()
    fun getMutableLiveData(): MutableLiveData<List<User>?> {
        val apiService = apiService
        val call = apiService.userList
        call!!.enqueue(object : Callback<UserWrapper?> {
            override fun onResponse(call: Call<UserWrapper?>, response: Response<UserWrapper?>) {
                val userWrapper = response.body()
                if (userWrapper != null && userWrapper.user != null) {
                    Log.d("ListSize", "success")

                    users = userWrapper.user as ArrayList<User>?
                    Log.d("ListSize",users.toString() )

                    mutableLiveData.value = users
                }
            }

            override fun onFailure(call: Call<UserWrapper?>, t: Throwable) {
                Log.d("ListSize", " - > Error    " + t.message)
            }
        })
        return mutableLiveData
    }

}