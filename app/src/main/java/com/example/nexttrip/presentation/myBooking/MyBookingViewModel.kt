package com.example.nexttrip.presentation.myBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.domain.repository.FlightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyBookingViewModel @Inject constructor(
    private val repository: FlightRepository
) : ViewModel() {

    var ticketInfo = MutableStateFlow<List<TicketEntity>>(emptyList())
        private set

    fun getTicketInfo() = viewModelScope.launch {
        ticketInfo.value = repository.getFlightBookingList()
    }
}