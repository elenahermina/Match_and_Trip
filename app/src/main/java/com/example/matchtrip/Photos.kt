package com.example.matchtrip

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = Trip::class,
        parentColumns = arrayOf("tripId"),
        childColumns = arrayOf("fkTripId"),
        onDelete = ForeignKey.SET_NULL
    )]
)

data class Photos(
    val photoName: String,
    val imageId: Int,
    var fkTripId: Long? = null,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image: ByteArray? = null
) {

    @PrimaryKey(autoGenerate = true)
    var photosId: Long = 0
}
