package com.example.kotlinmvvmexample.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface userDao {

    @Query("SELECT * FROM userTbl")
    fun allStudents(): List<Userdata>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student:Userdata)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertAll(kist: List<Userdata>?)

}
