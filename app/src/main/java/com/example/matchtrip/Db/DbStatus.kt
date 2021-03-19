package com.example.matchtrip.Db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbStatus(@PrimaryKey val id : Int, var created : Boolean)