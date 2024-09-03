package com.example.nexttrip.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nexttrip.data.local.dao.HotelBookingDao
import com.example.nexttrip.data.local.dao.TicketDao
import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.domain.model.hotelbooking.HotelBookingData

@Database(
    entities = [TicketEntity::class, HotelBookingData::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ticketDao(): TicketDao
    abstract fun hotelBookingDao(): HotelBookingDao

    companion object {
        operator fun invoke(context: Context) = buildDatabase(context)

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "NextTrip.db"
            ).build()
        }
    }
}