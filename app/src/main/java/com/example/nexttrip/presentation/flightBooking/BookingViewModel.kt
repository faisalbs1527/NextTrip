package com.example.nexttrip.presentation.flightBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.utils.currentDate
import com.example.nexttrip.utils.nextDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BookingViewModel : ViewModel() {

    var selectedItem = MutableStateFlow(0)
        private set

    var departureDate = MutableStateFlow(currentDate)
        private set

    var returnDate = MutableStateFlow(nextDate)
        private set

    var totalTraveler = MutableStateFlow(1)
        private set

    var adult = MutableStateFlow(1)
        private set

    var children = MutableStateFlow(0)
        private set

    var infant = MutableStateFlow(0)
        private set

    var selectedClass = MutableStateFlow(2)
        private set

    fun onUpdateDepartureDate(date: String) = viewModelScope.launch {
        departureDate.value = date
    }

    fun onUpdateReturnDate(date: String) = viewModelScope.launch {
        returnDate.value = date
    }

    fun onUpdateSelectedItem(item: Int) = viewModelScope.launch {
        selectedItem.value = item
    }

    fun onUpdateTotalTravelerCount(count: Int) = viewModelScope.launch {
        totalTraveler.value = count
    }

    fun onUpdateAdultCount(count: Int) = viewModelScope.launch {
        adult.value = count
    }

    fun onUpdateChildrenCount(count: Int) = viewModelScope.launch {
        children.value = count
    }

    fun onUpdateInfantCount(count: Int) = viewModelScope.launch {
        infant.value = count
    }

    fun onUpdateClass(type: Int) = viewModelScope.launch {
        selectedClass.value = type
    }
}