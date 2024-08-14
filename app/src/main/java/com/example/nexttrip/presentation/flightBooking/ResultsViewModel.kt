package com.example.nexttrip.presentation.flightBooking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.toFlightsData
import com.example.nexttrip.domain.repository.FlightRepository
import com.example.nexttrip.presentation.model.FlightsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val repository: FlightRepository
) : ViewModel() {
    private val _flightList = MutableStateFlow<List<FlightsData>>(emptyList())
    val flightList: StateFlow<List<FlightsData>> = _flightList

    private val _returnList = MutableStateFlow<List<FlightsData>>(emptyList())
    val returnList: StateFlow<List<FlightsData>> = _returnList

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFlights(arrivalAirport: String, departureAirport: String, classType: String) =
        viewModelScope.launch {
            val response = repository.getFlights()
            val flights = response.map { it.toFlightsData() }
            _flightList.value = flights.filter { flight ->
                flight.departureAirport == departureAirport &&
                        flight.arrivalAirport == arrivalAirport &&
                        flight.classType == classType
            }
            _returnList.value = flights.filter { flight ->
                flight.arrivalAirport == departureAirport &&
                        flight.departureAirport == arrivalAirport &&
                        flight.classType == classType
            }
        }
}