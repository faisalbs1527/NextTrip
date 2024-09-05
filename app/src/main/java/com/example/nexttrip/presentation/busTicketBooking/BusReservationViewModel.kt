package com.example.nexttrip.presentation.busTicketBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.busTicketBooking.SeatData
import com.example.nexttrip.domain.model.busTicketBooking.toAvailableBusData
import com.example.nexttrip.domain.model.busTicketBooking.toCityData
import com.example.nexttrip.domain.repository.BusTicketRepository
import com.example.nexttrip.presentation.model.AvailableBusData
import com.example.nexttrip.presentation.model.CityData
import com.example.nexttrip.utils.currentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusReservationViewModel @Inject constructor(
    private val repository: BusTicketRepository
) : ViewModel() {
    private var cityList = MutableStateFlow<List<CityData>>(emptyList())

    var citiesToShow = MutableStateFlow<List<CityData>>(emptyList())
        private set

    var route = MutableStateFlow("")
        private set

    var travelDate = MutableStateFlow(currentDate)
        private set
    private var fromLoc = MutableStateFlow("")
    private var toLoc = MutableStateFlow("")

    var availableBuses = MutableStateFlow<List<AvailableBusData>>(emptyList())
        private set

    fun getCities() = viewModelScope.launch {
        val response = repository.getCityList()
        cityList.value = response.districts.map { it.toCityData() }
    }

    fun updateSuggestions(query: String) {
        citiesToShow.value = cityList.value.filter { it.name.contains(query, ignoreCase = true) }
        citiesToShow.value = citiesToShow.value.take(3)
    }

    fun updateTravelDate(date: String) = viewModelScope.launch {
        travelDate.value = date
    }

    fun updateRoute(from: String, to: String) = viewModelScope.launch {
        route.value = "$from-$to"
        fromLoc.value = from
        toLoc.value = to
    }

    fun getBuses() = viewModelScope.launch {
        val response = repository.getAvailableBuses(route.value)
        response?.forEach { busCompany ->
            availableBuses.value += busCompany.buses.map {
                it.toAvailableBusData(
                    busCompany.companyName,
                    fromLoc.value,
                    toLoc.value,
                    getAvailableSeats(it.seats)
                )
            }
        }
    }

    fun clearBusList() = viewModelScope.launch {
        availableBuses.value = emptyList()
    }

    fun isAllOk(from: String, to: String): Boolean {
        return from.isNotEmpty() && to.isNotEmpty()
    }

    private fun getAvailableSeats(seatList: List<SeatData>): Int {
        var seats = 0
        seatList.forEach {
            if (it.status == "Available") {
                seats++
            }
        }
        return seats
    }
}