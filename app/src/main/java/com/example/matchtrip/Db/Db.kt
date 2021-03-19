package com.example.matchtrip.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.matchtrip.R
import com.example.matchtrip.Trip
import com.example.matchtrip.dao.DbStatusDao
import com.example.matchtrip.dao.TripDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Trip::class, DbStatus:: class], version = 1)
abstract class Db : RoomDatabase() {

    abstract fun tripDao(): TripDao
    abstract fun dbStatusDao(): DbStatusDao

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

        private fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(Dispatchers.IO).launch {
                        INSTANCE?.tripDao()?.insert(Trip( "European Dream (10 Days)", R.mipmap.europe))
                        INSTANCE?.tripDao()?.insert(Trip( "Thai Island Hopper East (9 Days)", R.mipmap.thailanda))
                        INSTANCE?.tripDao()?.insert(Trip("Cycle Tour In Albania - UNESCO 10 Day Tour", R.mipmap.albania))
                        INSTANCE?.tripDao()?.insert(Trip( "New Zealand: Best of the North Island", R.mipmap.newzeeland))

                        INSTANCE?.dbStatusDao()?.insert(DbStatus(0, true))

                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }
}