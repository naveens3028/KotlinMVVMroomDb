package com.example.kotlinmvvmexample.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTbl")
data class Userdata (
    @PrimaryKey(autoGenerate = true)
    var id:Long?,

    @ColumnInfo(name = "name")
    var names: String? = null,

    @ColumnInfo(name = "email")
    var email: String? = null,

    @ColumnInfo(name = "phone")
    var phone: String? = null)
