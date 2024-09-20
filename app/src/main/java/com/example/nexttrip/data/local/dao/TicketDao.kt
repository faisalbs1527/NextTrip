package com.example.nexttrip.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.domain.model.busTicketBooking.BusTicketEntity

@Dao
interface TicketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFlightBookingInfo(info: TicketEntity)

    @Query("SELECT * FROM flightBooking")
    suspend fun getFlightBookingInfo(): List<TicketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBusBookingInfo(info: BusTicketEntity)

    @Query("SELECT * FROM busBooking")
    suspend fun getBusBookingInfo(): List<BusTicketEntity>
}