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

class CreateUserFragmentViewModel(application: Application) : AndroidViewModel(application) {
  //  val userList: MutableLiveData<User> = MutableLiveData()
    private val db = Db.getDatabase(application)

   suspend fun insertUser(user: User) {
       withContext(Dispatchers.IO){
           db.userDao().insert(user)
            }
        }
    }
