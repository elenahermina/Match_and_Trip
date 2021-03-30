package com.example.matchtrip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.matchtrip.User
import com.example.matchtrip.db.Db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditUserFragmentViewModel (application: Application) : AndroidViewModel(application){
    private val db = Db.getDatabase(application)
    val userDetail: MutableLiveData<User> = MutableLiveData()

    fun userDetails(userId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val result = Db.getDatabase(getApplication()).userDao().getUserById(userId)
            withContext(Dispatchers.Main) {
                userDetail.value = result
            }
        }
    }

    fun insertUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            Db.getDatabase(getApplication()).userDao().insert(user)
        }
    }
}