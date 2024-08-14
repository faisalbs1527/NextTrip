package com.example.nexttrip.presentation.flightBooking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.utils.currentDate
import com.example.nexttrip.utils.nextDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel : ViewModel() {

    private val _selectedItem = MutableStateFlow(0)
    val selectedItem: StateFlow<Int> = _selectedItem

    private val _departureDate = MutableStateFlow(currentDate)
    val departureDate: StateFlow<String> = _departureDate

    private val _returnDate = MutableStateFlow(nextDate)
    val returnDate: StateFlow<String> = _returnDate

    private val _totalTraveler = MutableStateFlow(1)
    val totalTraveler: StateFlow<Int> = _totalTraveler

    private val _adult = MutableStateFlow(1)
    val adult: StateFlow<Int> = _adult

    private val _children = MutableStateFlow(0)
    val children: StateFlow<Int> = _children

    private val _infant = MutableStateFlow(0)
    val infant: StateFlow<Int> = _infant

    private val _selectedClass = MutableStateFlow(2)
    val selectedClass: StateFlow<Int> = _selectedClass

    fun onUpdateDepartureDate(date: String) = viewModelScope.launch {
        _departureDate.value = date
    }

    fun onUpdateReturnDate(date: String) = viewModelScope.launch {
        _returnDate.value = date
    }

    fun onUpdateSelectedItem(item: Int) = viewModelScope.launch {
        _selectedItem.value = item
    }

    fun onUpdateTotalTravelerCount(count: Int) = viewModelScope.launch {
        _totalTraveler.value = count
    }

    fun onUpdateAdultCount(count: Int) = viewModelScope.launch {
        _adult.value = count
    }

    fun onUpdateChildrenCount(count: Int) = viewModelScope.launch {
        _children.value = count
    }

    fun onUpdateInfantCount(count: Int) = viewModelScope.launch {
        _infant.value = count
    }

    fun onUpdateClass(type: Int) = viewModelScope.launch {
        _selectedClass.value = type
    }
}