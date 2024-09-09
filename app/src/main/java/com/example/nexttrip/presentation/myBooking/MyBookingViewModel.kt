package com.example.nexttrip.presentation.myBooking

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.domain.repository.FlightRepository
import com.example.nexttrip.utils.toImageBitmap
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

    var currTicket = MutableStateFlow<ImageBitmap?>(null)
    var currTicketName = MutableStateFlow<String?>("")

    fun getTicketInfo() = viewModelScope.launch {
        ticketInfo.value = repository.getFlightBookingList()
    }

    fun getTicket(id: Int) = viewModelScope.launch {
        val currTicketInfo = ticketInfo.value.find {
            it.id == id
        }
        val imageByteArray = currTicketInfo?.ticket
        currTicket.value = imageByteArray?.toImageBitmap()
        currTicketName.value = currTicketInfo?.ticketName
    }
}