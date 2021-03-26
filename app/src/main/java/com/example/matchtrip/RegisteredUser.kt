package com.example.matchtrip

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RegisteredUser(var email: String) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}