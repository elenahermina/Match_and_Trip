package com.example.matchtrip.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.matchtrip.Db.DbStatus


@Dao
interface DbStatusDao {

    @Query("SELECT * FROM DbStatus")
    fun getAll(): List<DbStatus>

    @Query("SELECT * FROM DbStatus")
    fun getAllLive(): LiveData<List<DbStatus>>

    @Insert
    fun insert(dbStatus: DbStatus)
}