package com.example.nexttrip.presentation.hotelBooking

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.components.formatNumber
import com.example.nexttrip.domain.model.hotelbooking.HotelBookingData
import com.example.nexttrip.domain.model.hotelbooking.Room
import com.example.nexttrip.domain.model.hotelbooking.toAvailableHotel
import com.example.nexttrip.domain.model.hotelbooking.toAvailableRoomInfo
import com.example.nexttrip.domain.repository.HotelRepository
import com.example.nexttrip.presentation.model.AvailableHotelData
import com.example.nexttrip.presentation.model.AvailableRoomInfo
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

    var selectedRooms = MutableStateFlow<List<AvailableRoomInfo>>(emptyList())
        private set

    var availableRooms = MutableStateFlow<List<AvailableRoomInfo>>(emptyList())
        private set

    private var selectedHotelId = MutableStateFlow(0)

    var selectedHotel = MutableStateFlow(AvailableHotelData())

    fun onUpdateCheckIN(date: String) = viewModelScope.launch {
        checkIn.value = date
    }

    fun onUpdateCheckOut(date: String) = viewModelScope.launch {
        checkOut.value = date
    }

    fun updateRoomList(rooms: List<RoomData>) = viewModelScope.launch {
        roomList.value = rooms
    }

    fun updateSelectedHotelId(id: Int) = viewModelScope.launch {
        selectedHotelId.value = id
    }

    fun updateSelectedRooms(room: AvailableRoomInfo) = viewModelScope.launch {
        selectedRooms.value += room
    }

    fun getTotalActualPrice(): String {
        var actualPrice = 0
        selectedRooms.value.forEach {
            actualPrice += it.actualPrice
        }
        return "BDT " + formatNumber(actualPrice)
    }

    fun getTotalDiscountPrice(): String {
        var discountPrice = 0
        selectedRooms.value.forEach {
            discountPrice += it.discountPrice
        }
        return "BDT " + formatNumber(discountPrice)
    }

    @SuppressLint("DefaultLocale")
    fun getTotalGuests(): String {
        var total = 0
        for (room in roomList.value) {
            total += room.adult
            total += room.children
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

    fun getSelectedHotelInfo() = viewModelScope.launch {
        selectedHotel.value = hotelList.value.find { it.id == selectedHotelId.value }!!
    }

    fun getAvailableRooms(roomX: RoomData) = viewModelScope.launch {
        availableRooms.value = emptyList()
        val currHotel = hotelList.value.find { it.id == selectedHotelId.value }!!

        currHotel.rooms.forEach { roomY ->
            var alreadySelected = false
            selectedRooms.value.forEach {
                if (it.roomId == roomY.room_id) {
                    alreadySelected = true
                }
            }
            if (!alreadySelected && roomX.adult <= roomY.capacity) {
                availableRooms.value += roomY.toAvailableRoomInfo()
            }
        }
        println(availableRooms)
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

    fun saveBookingData() = viewModelScope.launch {
        repository.saveHotelBookingInfo(
            HotelBookingData(
                checkIn = checkIn.value,
                checkOut = checkOut.value,
                bookingDate = currentDate,
                hotelName = selectedHotel.value.name,
                rooms = selectedRooms.value.size
            )
        )
    }

    private fun getRoomIds(): List<Int> {
        val rooms = emptyList<Int>().toMutableList()
        selectedRooms.value.forEach {
            rooms += it.roomId
        }
        return rooms
    }
}