package com.example.matchtrip.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.matchtrip.R
import com.example.matchtrip.Trip
import com.example.matchtrip.User
import com.example.matchtrip.dao.DbStatusDao
import com.example.matchtrip.dao.TripDao
import com.example.matchtrip.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Trip::class, DbStatus:: class, User::class], version = 1)
abstract class Db : RoomDatabase() {

    abstract fun tripDao(): TripDao
    abstract fun dbStatusDao(): DbStatusDao
    abstract fun userDao(): UserDao

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
                        INSTANCE?.tripDao()?.insert(Trip(
                            "European Dream (10 Days)",
                            R.mipmap.europe,
                            "01 Aug 2021 - 10 Aug 2021",
                            "Tour Operator: Trafalgar, Max group size: 50, Age range: 5 to 99, Operated in: English",
                            R.mipmap.map_thai,
                            "Start in Rome and end in London! With the Discovery tour European Dream (10 Days), you have a 10 days tour package taking you through Rome, Italy and 6 other destinations in Europe. European Dream (10 Days) includes accommodation, an expert guide, meals, transport and more.",
                             R.mipmap.lisa))
                        INSTANCE?.tripDao()?.insert(Trip(
                            "Thai Island Hopper East (9 Days)",
                            R.mipmap.thailanda,
                            "20 Jul 2021 - 29 Jul 2021",
                            "Tour Operator: Contiki, Max group size: 30, Age range 18 to 35, Operated in: English ",
                            R.mipmap.map_thai,
                            "Start and end in Koh Samui! With the Beach tour Thai Island Hopper East (9 Days), you have a 9 days tour package taking you through Koh Samui, Thailand and 2 other destinations in Thailand. Thai Island Hopper East (9 Days) includes accommodation in a hotel as well as an expert guide, meals, transport and more.",
                            R.mipmap.lisa))
                        INSTANCE?.tripDao()?.insert(Trip(
                            "Cycle Tour In Albania - UNESCO 10 Day Tour",
                            R.mipmap.albania,
                            "01 Jul 2021 - 10 Jul 2021",
                            "Tour Operator: Cycle Albania, Max group size: 20, Age range: 16 to 99, Operated in: English",
                            R.mipmap.map_thai,
                            "Start in Tirana and end in Vlore! With the Bicycle tour Cycle Tour In Albania - UNESCO 10 Day Tour, you have a 10 days tour package taking you through Tirana, Albania and 8 other destinations in Albania. Cycle Tour In Albania - UNESCO 10 Day Tour includes accommodation in a hotel as well as an expert guide, meals, transport and more.",
                            R.mipmap.lisa))
                        INSTANCE?.tripDao()?.insert(Trip(
                            "New Zealand: Best of the North Island (7 Days)",
                            R.mipmap.newzeeland,
                            "05 Jul 2021 - 12 Jul 2021",
                            "Tour Operator: G Adventure, Max group size: 20, Age range: 18 to 39, Operated in: English",
                            R.mipmap.map_thai,
                            "Start in Auckland and end in Wellington! With the In-depth Cultural tour New Zealand: Best of the North Island, you have a 7 days tour package taking you through Auckland, New Zealand and 4 other destinations in New Zealand. New Zealand: Best of the North Island includes accommodation in a hostel as well as an expert guide, meals, transport and more.",
                            R.mipmap.lisa))

                        INSTANCE?.dbStatusDao()?.insert(DbStatus(0, true))

                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }


    }
}