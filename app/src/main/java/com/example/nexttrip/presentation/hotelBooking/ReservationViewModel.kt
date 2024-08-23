package com.example.nexttrip.presentation.hotelBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.utils.currentDate
import com.example.nexttrip.utils.nextDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ReservationViewModel : ViewModel() {
    var checkIn = MutableStateFlow(currentDate)
        private set

    var checkOut = MutableStateFlow(nextDate)
        private set

    fun onUpdateCheckIN(date: String) = viewModelScope.launch {
        checkIn.value = date
    }

    fun onUpdateCheckOut(date: String) = viewModelScope.launch {
        checkOut.value = date
    }

}