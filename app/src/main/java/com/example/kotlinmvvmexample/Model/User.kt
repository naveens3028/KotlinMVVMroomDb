package com.example.kotlinmvvmexample.Model

import com.google.gson.annotations.SerializedName


class User {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("phone")
    var phone: String? = null

}