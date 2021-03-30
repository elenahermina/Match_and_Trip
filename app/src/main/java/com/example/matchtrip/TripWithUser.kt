package com.example.matchtrip

import androidx.room.Embedded

data class TripWithUser (
    @Embedded var user: User,
    @Embedded var trip: Trip
)