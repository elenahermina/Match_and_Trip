package com.example.matchtrip

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val email: String, val password: String, val firstName: String, val lastName: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 }