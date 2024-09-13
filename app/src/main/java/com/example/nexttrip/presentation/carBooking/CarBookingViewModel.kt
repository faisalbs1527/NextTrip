package com.example.nexttrip.presentation.carBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.carBooking.LocationDetails
import com.example.nexttrip.domain.model.carBooking.toAvailableCarData
import com.example.nexttrip.domain.repository.CarBookingRepository
import com.example.nexttrip.presentation.model.AvailableCarData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarBookingViewModel @Inject constructor(
    private val repository: CarBookingRepository
) : ViewModel() {

    private var currLocationId = MutableStateFlow(0)

    var carLocations = MutableStateFlow<List<AvailableCarData>>(emptyList())
        private set
    var locationsDhaka = MutableStateFlow<List<LocationDetails>>(emptyList())
        private set
    var locationsToShow = MutableStateFlow<List<LocationDetails>>(emptyList())
        private set
    var pickUp = MutableStateFlow(LocationDetails())
        private set
    var destination = MutableStateFlow(LocationDetails())
        private set
    var currLocation = MutableStateFlow(LocationDetails())
        private set

    fun clearState() = viewModelScope.launch {
        pickUp.value = LocationDetails()
        destination.value = LocationDetails()
    }

    fun updateCurrLocationId(id: Int) = viewModelScope.launch {
        currLocationId.value = id
    }

    fun updateCurrLocation(lat: Double, long: Double, address: String) = viewModelScope.launch {
        currLocation.value = LocationDetails(lat, long, address)
    }

    fun updateLocation(location: LocationDetails) = viewModelScope.launch {
        if (currLocationId.value == 1) {
            pickUp.value = location
        } else {
            destination.value = location
        }
    }

    fun updatePickUp(location: LocationDetails) = viewModelScope.launch {
        pickUp.value = location
    }

    fun updateDestination(location: LocationDetails) = viewModelScope.launch {
        destination.value = location
    }

    fun getCurrCarLocations() = viewModelScope.launch {
        val response = repository.getCurrentCarLocations()
        carLocations.value = response.carLocations.map { it.toAvailableCarData() }
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