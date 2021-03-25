package com.example.matchtrip

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Trip( var name : String,
                 var tripPhotoId: Int,
                 var dates: String? = null,
                 var details: String? = null,
                 var map: Int? = null,
                 var intinerary: String? = null,
                 var review: Int? = null
){
    @PrimaryKey(autoGenerate = true)
    var tripId : Long = 0 }