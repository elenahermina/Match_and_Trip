package com.example.matchtrip

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [], version = 1)
abstract class Db : RoomDatabase() {


    companion object {

        @Volatile
        private var INSTANCE: Db? = null

        fun getDatabase(context: Context): Db {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return INSTANCE as Db
                }
                val roomBuilder =
                    Room.databaseBuilder(context.applicationContext, Db::class.java, "database-db")
                roomBuilder.addCallback(getCallback())
                INSTANCE = roomBuilder.build()
                return INSTANCE as Db
            }
        }

        private fun getCallback(): Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(Dispatchers.IO).launch {

                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }
}