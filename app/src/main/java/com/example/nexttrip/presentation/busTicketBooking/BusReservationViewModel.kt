package com.example.nexttrip.presentation.busTicketBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.busTicketBooking.toCityData
import com.example.nexttrip.domain.repository.BusTicketRepository
import com.example.nexttrip.presentation.model.CityData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusReservationViewModel @Inject constructor(
    private val repository: BusTicketRepository
) : ViewModel() {
    var cityList = MutableStateFlow<List<CityData>>(emptyList())
        private set

    var citiesToShow = MutableStateFlow<List<CityData>>(emptyList())
        private set

    fun getCities() = viewModelScope.launch {
        val response = repository.getCityList()
        cityList.value = response.districts.map { it.toCityData() }
    }

    fun updateSuggestions(query: String) {
        citiesToShow.value = cityList.value.filter { it.name.contains(query, ignoreCase = true) }
        citiesToShow.value = citiesToShow.value.take(3)
    }
}