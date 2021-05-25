package com.example.matchtrip.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.matchtrip.TripWithUser
import com.example.matchtrip.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User")
    fun getAllLive(): LiveData<List<User>>

    @Insert
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user WHERE userId= :userId ")
    fun getUserById(userId: Long): User

    @Query("SELECT * FROM user INNER JOIN trip ON user.userId = trip.fkUserId")
    fun getUserByTrip(): List<TripWithUser>
}
