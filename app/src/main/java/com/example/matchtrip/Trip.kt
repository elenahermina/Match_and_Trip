package com.example.matchtrip


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Trip( var name : String,
                 var photoId: Int,
                 var dates: String?,
                 var details: String?,
                 var map: Int?,
                 var intinerary: String?,
                 var review: Int?){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0 }