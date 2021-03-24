package com.example.matchtrip.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.matchtrip.Trip


@Dao
interface TripDao {

  @Query("SELECT * FROM Trip")
    fun getAll(): List<Trip>

    @Query("SELECT * FROM Trip")
    fun getAllLive(): LiveData<List<Trip>>

    @Insert
    fun insert(trip: Trip)

    @Query("SELECT * FROM Trip WHERE id ==:tripId ")
    fun getTripById(tripId: Int): Trip
}