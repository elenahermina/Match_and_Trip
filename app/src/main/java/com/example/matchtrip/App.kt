package com.example.matchtrip

import android.app.Application
import com.example.matchtrip.Db.Db
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Db.getDatabase(this)
        FirebaseApp.initializeApp(applicationContext)
    }
}