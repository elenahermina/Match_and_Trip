package com.example.matchtrip.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.matchtrip.User
import com.example.matchtrip.db.Db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateUserFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Db.getDatabase(application)

    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            db.userDao().insert(user)
        }
    }

}
