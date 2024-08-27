package com.example.nexttrip.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nexttrip.domain.model.TicketEntity

@Dao
interface TicketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFlightBookingInfo(info: TicketEntity)

    @Query("SELECT * FROM flightBooking")
    suspend fun getFlightBookingInfo(): List<TicketEntity>
}