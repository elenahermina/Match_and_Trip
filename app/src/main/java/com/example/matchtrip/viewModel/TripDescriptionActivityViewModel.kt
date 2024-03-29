package com.example.matchtrip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.matchtrip.Photos
import com.example.matchtrip.Trip
import com.example.matchtrip.db.Db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TripDescriptionActivityViewModel(application: Application) : AndroidViewModel(application) {

    val tripDetail: MutableLiveData<Trip> = MutableLiveData()
    private val db = Db.getDatabase(application)

    fun tripDetails(tripId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = Db.getDatabase(getApplication()).tripDao().getTripById(tripId)
            withContext(Dispatchers.Main) {
                tripDetail.value = result
            }
        }
    }

    suspend fun cargarPhotos(trip: Trip): List<Photos> {
        return withContext(Dispatchers.IO) {
            Db.getDatabase((getApplication())).photosDao().getPhotosByTripId(trip.tripId)

        }
    }
}