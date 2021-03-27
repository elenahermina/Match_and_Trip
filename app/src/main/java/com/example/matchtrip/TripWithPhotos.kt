package com.example.matchtrip

import androidx.room.Embedded

data class TripWithPhotos (
    @Embedded var trip: Trip,
    @Embedded var photos: Photos)