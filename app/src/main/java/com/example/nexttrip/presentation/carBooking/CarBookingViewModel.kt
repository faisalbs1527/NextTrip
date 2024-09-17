package com.example.nexttrip.presentation.carBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.carBooking.LocationDetails
import com.example.nexttrip.domain.model.carBooking.TripRoute
import com.example.nexttrip.domain.model.carBooking.toAvailableCarData
import com.example.nexttrip.domain.repository.CarBookingRepository
import com.example.nexttrip.presentation.model.AvailableCarData
import com.example.nexttrip.utils.MapUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class CarBookingViewModel @Inject constructor(
    private val repository: CarBookingRepository
) : ViewModel() {

    private var currLocationId = MutableStateFlow(0)
    private var locationsDhaka = MutableStateFlow<List<LocationDetails>>(emptyList())

    var bookingScreenState = MutableStateFlow(1)

    var carLocations = MutableStateFlow<List<AvailableCarData>>(emptyList())
        private set
    var locationsToShow = MutableStateFlow<List<LocationDetails>>(emptyList())
        private set
    var pickUp = MutableStateFlow(LocationDetails())
        private set
    var destination = MutableStateFlow(LocationDetails())
        private set
    var currLocation = MutableStateFlow(LocationDetails())
        private set
    var availableCars = MutableStateFlow<List<AvailableCarData>>(emptyList())
        private set
    var selectedCar = MutableStateFlow(AvailableCarData())
        private set

    fun clearState() = viewModelScope.launch {
        pickUp.value = LocationDetails()
        destination.value = LocationDetails()
    }

    fun updateBookingPageState(state: Int) = viewModelScope.launch {
        bookingScreenState.value = state
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

    fun findAvailableCars() = viewModelScope.launch {
        availableCars.value = carLocations.value.filter { cars ->
            checkRoutes(cars.availableRoutes)
        }
        availableCars.value = availableCars.value.map {
            it.copy(
                routeInfo = MapUtils.getDistanceAndDuration(
                    pickUp.value.latitude,
                    pickUp.value.longitude,
                    it.latitude,
                    it.longitude
                ),
                price = getPrice()
            )
        }
    }

    fun updateCarSelection(car: AvailableCarData) = viewModelScope.launch {
        selectedCar.value = car
    }

    private suspend fun getPrice(): Int {
        var price = 0
        val routeInfo = MapUtils.getDistanceAndDuration(
            pickUp.value.latitude,
            pickUp.value.longitude,
            destination.value.latitude,
            destination.value.longitude
        )
        price = ceil(routeInfo.distance * 45).toInt()
        return price
    }

    private fun checkRoutes(routes: List<TripRoute>): Boolean {
        for (route in routes) {
            if (route.fromLocation == pickUp.value && route.toLocation == destination.value) {
                return true
            }
        }
        return false
    }
}