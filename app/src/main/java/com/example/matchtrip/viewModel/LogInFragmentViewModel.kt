package com.example.matchtrip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.matchtrip.db.Db
import com.example.matchtrip.User
import com.example.matchtrip.fragment.UserProfileFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

    suspend fun verifyUser(email: String) : Boolean  {
        return viewModelScope.async(Dispatchers.IO) {
            val allRegisteredUser =  Db.getDatabase(getApplication()).registeredUserDao().getAll()
            allRegisteredUser.forEach {
                if (it.email.contentEquals(email))
                    return@async true
            }
            return@async false
        }.await()

    }
}