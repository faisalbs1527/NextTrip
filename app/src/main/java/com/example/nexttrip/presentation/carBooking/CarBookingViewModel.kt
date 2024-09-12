package com.example.nexttrip.presentation.carBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.carBooking.LocationDhakaItem
import com.example.nexttrip.domain.model.carBooking.toGeoLocation
import com.example.nexttrip.domain.repository.CarBookingRepository
import com.example.nexttrip.presentation.model.GeoLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarBookingViewModel @Inject constructor(
    private val repository: CarBookingRepository
) : ViewModel() {

    var carLocations = MutableStateFlow<List<GeoLocation>>(emptyList())
        private set
    var locationsDhaka = MutableStateFlow<List<LocationDhakaItem>>(emptyList())
        private set
    var locationsToShow = MutableStateFlow<List<LocationDhakaItem>>(emptyList())
        private set
    var pickUp = MutableStateFlow(LocationDhakaItem())
        private set
    var destination = MutableStateFlow(LocationDhakaItem())
        private set

    fun clearState() = viewModelScope.launch {
        pickUp.value = LocationDhakaItem()
        destination.value = LocationDhakaItem()
    }

    fun updatePickUp(location: LocationDhakaItem) = viewModelScope.launch {
        pickUp.value = location
    }

    fun updateDestination(location: LocationDhakaItem) = viewModelScope.launch {
        destination.value = location
    }

    fun getCurrCarLocations() = viewModelScope.launch {
        val response = repository.getCurrentCarLocations()
        carLocations.value = response.carLocations.map { it.toGeoLocation() }
    }

    fun getLocationsInDhaka() = viewModelScope.launch {
        val response = repository.getLocationsDhaka()
        locationsDhaka.value = response.locations
    }

    fun updateSuggestions(query: String) {
        locationsToShow.value =
            locationsDhaka.value.filter { it.name.contains(query, ignoreCase = true) }
        locationsToShow.value = locationsToShow.value.take(5)
    }
}