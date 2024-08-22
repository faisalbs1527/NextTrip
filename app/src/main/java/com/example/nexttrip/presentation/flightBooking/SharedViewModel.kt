package com.example.nexttrip.presentation.flightBooking

import androidx.lifecycle.ViewModel
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.presentation.model.PassengerData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
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
}