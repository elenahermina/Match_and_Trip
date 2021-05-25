package com.example.matchtrip

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class User(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    var aboutMe: String? = null,
    var photo: Int? = null,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image: ByteArray? = null,
    var age: Int? = null
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0
}