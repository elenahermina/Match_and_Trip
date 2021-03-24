package com.example.matchtrip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.matchtrip.db.Db
import com.example.matchtrip.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogInFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val userList: MutableLiveData<List<User>> = MutableLiveData()
    private val db = Db.getDatabase(application)

    fun getAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = Db.getDatabase(getApplication()).userDao().getAll()
            withContext(Dispatchers.Main) {
                userList.value = result
            }
        }
    }
}