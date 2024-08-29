package com.example.nexttrip.presentation.hotelBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.hotelbooking.Room
import com.example.nexttrip.domain.model.hotelbooking.toAvailableHotel
import com.example.nexttrip.domain.repository.HotelRepository
import com.example.nexttrip.presentation.model.AvailableHotelData
import com.example.nexttrip.presentation.model.RoomData
import com.example.nexttrip.utils.currentDate
import com.example.nexttrip.utils.nextDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val repository: HotelRepository
) : ViewModel() {
    var checkIn = MutableStateFlow(currentDate)
        private set

    var checkOut = MutableStateFlow(nextDate)
        private set

    var roomList = MutableStateFlow(listOf(RoomData()))
        private set

    var hotelList = MutableStateFlow<List<AvailableHotelData>>(emptyList())
        private set

    fun onUpdateCheckIN(date: String) = viewModelScope.launch {
        checkIn.value = date
    }

    fun onUpdateCheckOut(date: String) = viewModelScope.launch {
        checkOut.value = date
    }

    fun updateRoomList(rooms: List<RoomData>) = viewModelScope.launch {
        roomList.value = rooms
    }

    fun getTotalGuests(): String {
        var total = 0
        for (room in roomList.value) {
            total += room.totalGuest
        }
        return String.format("%02d", total)
    }

    fun getAvailableHotels() = viewModelScope.launch {
        val response = repository.getHotels()
        val availableHotels = response.hotels.filter {
            checkAvailability(it.rooms)
        }
        hotelList.value = availableHotels.map { it.toAvailableHotel() }
    }

    private fun checkAvailability(hotelRooms: List<Room>): Boolean {
        for (roomX in roomList.value) {
            var isAvailable = false
            for (roomY in hotelRooms) {
                if (roomX.adult <= roomY.capacity) {
                    isAvailable = true
                    break
                }
            }
            if (!isAvailable) return false
        }
        return true
    }
}