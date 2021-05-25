package com.example.matchtrip

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(foreignKeys = [ForeignKey(entity = User::class,
    parentColumns = arrayOf("userId"),
    childColumns = arrayOf("fkUserId"),
    onDelete = ForeignKey.SET_NULL)])

data class Trip( var name : String,
                 var tripPhotoId: Int?,
                 var datesInicio: Long?,
                 var datesFinal: Long?,
                 var details: String? = null,
                 var map: Int? = null,
                 var intinerary: String? = null,
                 var review: Int? = null,
                 var fkUserId: Long? = null
){
    @PrimaryKey(autoGenerate = true)
    var tripId : Long = 0 }