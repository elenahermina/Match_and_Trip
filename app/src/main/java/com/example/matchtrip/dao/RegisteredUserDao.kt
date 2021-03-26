package com.example.matchtrip.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.matchtrip.RegisteredUser

@Dao
interface RegisteredUserDao {

    @Query("SELECT * FROM RegisteredUser")
    fun getAllLive(): LiveData<List<RegisteredUser>>

    @Query("SELECT * FROM RegisteredUser")
    fun getAll(): List<RegisteredUser>

    @Insert
    fun insert(registeredUser: RegisteredUser)

    @Delete
    fun delete(registeredUser: RegisteredUser)
}