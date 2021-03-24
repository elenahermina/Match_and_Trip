package com.example.matchtrip

import android.app.Application
import com.example.matchtrip.db.Db


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Db.getDatabase(this)

    }
}