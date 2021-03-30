package com.example.matchtrip

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Trip::class,
    parentColumns = arrayOf("tripId"),
    childColumns = arrayOf("fkTripId"),
    onDelete = ForeignKey.SET_NULL)])

data class User(val email: String, val password: String, val firstName: String, val lastName: String, var aboutMe: String? = null, var fkTripId: Long? = null, @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image: ByteArray? = null) {
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0 }