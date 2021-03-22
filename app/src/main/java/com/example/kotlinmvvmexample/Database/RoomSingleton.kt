package com.example.kotlinmvvmexample.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Userdata::class), version = 1, exportSchema = false)
abstract class RoomSingleton : RoomDatabase() {
    abstract fun userdao():userDao

    companion object {
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context: Context): RoomSingleton {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RoomSingleton::class.java,
                    "roomdb")
                    .build()
            }
            return INSTANCE as RoomSingleton
        }
    }
}
