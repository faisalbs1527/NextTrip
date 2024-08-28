package com.example.nexttrip.presentation.flightBooking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.domain.repository.FlightRepository
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.presentation.model.PassengerData
import com.example.nexttrip.utils.convertToISO8601
import com.example.nexttrip.utils.getTime
import com.example.nexttrip.utils.ticketDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: FlightRepository
) : ViewModel() {
    var departureFlight = MutableStateFlow<FlightsData>(FlightsData())
        private set
    var returnFlight = MutableStateFlow<FlightsData>(FlightsData())
        private set
    var bookingdata = MutableStateFlow<FlightBookingData>(FlightBookingData())
        private set
    var passengerList = MutableStateFlow<List<PassengerData>>(emptyList())
        private set
    var selectedSeatsDeparture = MutableStateFlow<String>("")

    var selectedSeatsReturn = MutableStateFlow<String>("")

    fun updateDepartureFlight(flight: FlightsData) {
        departureFlight.value = flight
    }

    fun updateReturnFlight(flight: FlightsData) {
        returnFlight.value = flight
    }

    fun updateBookingData(data: FlightBookingData) {
        bookingdata.value = data
    }

    fun updatePassengerList(passengers: List<PassengerData>) {
        passengerList.value = passengers
    }

    fun updateSelectedSeatsDeparture(seats: String) {
        selectedSeatsDeparture.value = seats
    }

    fun updateSelectedSeatsReturn(seats: String) {
        selectedSeatsReturn.value = seats
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveBookingInfo(ticket: ByteArray) = viewModelScope.launch {
        val currDateTime = ticketDate()
        val ticketEntityDeparture = TicketEntity(
            flightNo = departureFlight.value.flightNumber,
            departureCity = bookingdata.value.departureCity,
            departureTime = convertToISO8601(
                bookingdata.value.departureDate,
                getTime(departureFlight.value.departureTime)
            ),
            arrivalCity = bookingdata.value.arrivalCity,
            arrivalTime = convertToISO8601(
                bookingdata.value.departureDate,
                getTime(departureFlight.value.arrivalTime)
            ),
            ticket = ticket,
            ticketName = "ticket_$currDateTime"
        )
        repository.saveFlightTicketInfo(ticketEntityDeparture)

        if (bookingdata.value.roundway) {
            val ticketEntityReturn = TicketEntity(
                flightNo = returnFlight.value.flightNumber,
                departureCity = bookingdata.value.arrivalCity,
                departureTime = convertToISO8601(
                    bookingdata.value.arrivalDate,
                    getTime(returnFlight.value.departureTime)
                ),
                arrivalCity = bookingdata.value.departureCity,
                arrivalTime = convertToISO8601(
                    bookingdata.value.arrivalDate,
                    getTime(returnFlight.value.arrivalTime)
                ),
                ticket = ticket,
                ticketName = "ticket_$currDateTime"
            )
            repository.saveFlightTicketInfo(ticketEntityReturn)
        }
    }
}