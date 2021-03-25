package com.example.matchtrip.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.matchtrip.Photos
import com.example.matchtrip.TripWithPhotos


@Dao
interface PhotosDao {

    @Query("SELECT * FROM Photos")
    fun getAll(): List<Photos>

    @Query("SELECT * FROM Photos")
    fun getAllLive(): LiveData<List<Photos>>

    @Insert
    fun insert(photos: Photos)

    @Insert
    fun insert(photos: List<Photos>)

    @Delete
    fun delete(photos: Photos)

    @Query("SELECT * FROM photos WHERE fkTripId = :tripId")
    fun getPhotosByTripId(tripId: Long): List<Photos>


    @Query("SELECT * FROM photos INNER JOIN trip ON photos.fkTripId = trip.tripId")
    fun getPhotosByTrip(): List<TripWithPhotos>
}