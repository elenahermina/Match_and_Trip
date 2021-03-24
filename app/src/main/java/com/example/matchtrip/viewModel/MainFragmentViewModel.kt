package com.example.matchtrip.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.matchtrip.db.Db
import com.example.matchtrip.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val tripList: MutableLiveData<List<Trip>> = MutableLiveData()
    private val db = Db.getDatabase(application)

    fun getAllTrip() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = Db.getDatabase(getApplication()).tripDao().getAll()
            withContext(Dispatchers.Main) {
                tripList.value = result
            }
        }
    }

    fun getTripById(id: Int): Trip {
        return db.tripDao().getTripById(id)
    }


}