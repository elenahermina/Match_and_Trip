package com.example.matchtrip.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.matchtrip.Trip
import com.example.matchtrip.db.Db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val tripPhotosList: MutableLiveData<List<Trip>> = MutableLiveData()
    private val db = Db.getDatabase(application)

    fun getAllTrip() {
        viewModelScope.launch(Dispatchers.IO) {
            changeTripListValueOnUi(Db.getDatabase(getApplication()).tripDao().getAll())
            Db.getDatabase(getApplication()).photosDao().getAll().forEach {
                it.fkTripId = Random.nextLong(8) + 1
                Db.getDatabase(getApplication()).tripDao().insert(listOf())
            }
            Db.getDatabase(getApplication()).photosDao().getPhotosByTrip().forEach {
                Log.d("DobleClass", it.toString())
            }
            val result = Db.getDatabase(getApplication()).tripDao().getAll()
            withContext(Dispatchers.Main) {
                tripPhotosList.value = result
            }
        }


    }


    private suspend fun changeTripListValueOnUi(list: List<Trip>) = withContext(Dispatchers.Main) {
        tripPhotosList.value = listOf()
    }

    fun getTripById(id: Int): List<Trip> {
        return db.tripDao().getAll()
    }


}