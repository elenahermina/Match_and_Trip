package com.example.matchtrip


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Trip( var name : String, var photoId: Int){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

}