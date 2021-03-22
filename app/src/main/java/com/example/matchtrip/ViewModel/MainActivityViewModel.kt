package com.example.matchtrip.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.matchtrip.Db.Db
import com.example.matchtrip.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val tripList : MutableLiveData<List<Trip>> = MutableLiveData()
    private val db = Db.getDatabase(application)

    fun getAllTrip(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = Db.getDatabase(getApplication()).tripDao().getAll()
            withContext(Dispatchers.Main) {
                tripList.value = result
            }

        }
    }

    fun getTripById(id: Int): LiveData<Trip> {
        return db.tripDao().getTripById(id)
    }
}