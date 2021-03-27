package com.example.matchtrip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.matchtrip.Trip
import com.example.matchtrip.db.Db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateTripFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Db.getDatabase(application)

    suspend fun insertTrip(trip: Trip) {
        withContext(Dispatchers.IO){
            db.tripDao().insert(trip)
        }
    }
}