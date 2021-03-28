package com.example.kotlinmvvmexample.Model

import com.google.gson.annotations.SerializedName


class UserWrapper {
    @SerializedName("data")
    var mData: List<User>? = null

    @SerializedName("error")
    var error: Boolean? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: String? = null

    var user: List<User>?
        get() = mData
        set(data) {
            mData = data as List<User>?
        }

}