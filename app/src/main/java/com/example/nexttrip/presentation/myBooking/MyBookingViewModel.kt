package com.example.nexttrip.presentation.myBooking

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.domain.model.busTicketBooking.BusTicketEntity
import com.example.nexttrip.domain.model.hotelbooking.HotelBookingData
import com.example.nexttrip.domain.repository.BusTicketRepository
import com.example.nexttrip.domain.repository.FlightRepository
import com.example.nexttrip.domain.repository.HotelRepository
import com.example.nexttrip.utils.toImageBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyBookingViewModel @Inject constructor(
    private val flightRepository: FlightRepository,
    private val busRepository: BusTicketRepository,
    private val hotelRepository: HotelRepository
) : ViewModel() {

    var selectedService = MutableStateFlow(1)
        private set

    var ticketInfo = MutableStateFlow<List<TicketEntity>>(emptyList())
        private set

    var busBookingList = MutableStateFlow<List<BusTicketEntity>>(emptyList())
        private set

    var hotelBookingList = MutableStateFlow<List<HotelBookingData>>(emptyList())
        private set

    var currTicket = MutableStateFlow<ImageBitmap?>(null)
    var currTicketName = MutableStateFlow<String?>("")

    fun updateSelectedService(serviceID: Int) = viewModelScope.launch {
        selectedService.value = serviceID
    }

    fun getTicketInfo() = viewModelScope.launch {
        ticketInfo.value = flightRepository.getFlightBookingList()
    }

    fun getTicket(id: Int) = viewModelScope.launch {
        val currTicketInfo = ticketInfo.value.find {
            it.id == id
        }
        val imageByteArray = currTicketInfo?.ticket
        currTicket.value = imageByteArray?.toImageBitmap()
        currTicketName.value = currTicketInfo?.ticketName
    }

    fun getBusBookingList() = viewModelScope.launch {
        busBookingList.value = busRepository.getBusBookingList()
    }

    fun getBusTicket(id: Int) = viewModelScope.launch {
        val currTicketInfo = busBookingList.value.find {
            it.id == id
        }
        val imageByteArray = currTicketInfo?.ticket
        currTicket.value = imageByteArray?.toImageBitmap()
        currTicketName.value = currTicketInfo?.ticketName
    }

    fun getHotelBookingList() = viewModelScope.launch {
        hotelBookingList.value = hotelRepository.getHotelBookingInfo()
    }
}