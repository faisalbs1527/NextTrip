package com.example.nexttrip.presentation.flightBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.toAirportsData
import com.example.nexttrip.domain.repository.DestinationRepository
import com.example.nexttrip.presentation.model.AirportsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: DestinationRepository
) : ViewModel() {
    private val _airportList = MutableStateFlow<List<AirportsData>>(emptyList())
    val airportList: StateFlow<List<AirportsData>> = _airportList

    fun getAirports() = viewModelScope.launch {
        val response = repository.getAirports()
        _airportList.value = response.map { it.toAirportsData() }
    }
}