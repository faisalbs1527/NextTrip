package com.example.nexttrip.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nexttrip.domain.model.hotelbooking.HotelBookingData

@Dao
interface HotelBookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHotelBookingInfo(info: HotelBookingData)

    @Query("SELECT * FROM hotelBooking")
    suspend fun getHotelBookingInfo(): List<HotelBookingData>
}