package com.example.matchtrip.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.matchtrip.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User")
    fun getAllLive(): LiveData<List<User>>

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}