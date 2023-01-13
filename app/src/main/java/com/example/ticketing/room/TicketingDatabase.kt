package com.example.ticketing.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ticketing.model.Event
import com.example.ticketing.model.Order


@Database(
    entities = [Event::class, Order::class],
    version = 1,
    exportSchema = true
)
abstract class TicketingDatabase:RoomDatabase() {
    abstract fun getTicketingDao() : TicketingDao
    companion object {
        private const val DB_NAME = "ticketing_database.db"
        @Volatile private var instance: TicketingDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TicketingDatabase::class.java,
            DB_NAME
        ).allowMainThreadQueries().build()
    }

}