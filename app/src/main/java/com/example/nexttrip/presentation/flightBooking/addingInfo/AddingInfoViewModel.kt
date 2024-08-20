package com.example.nexttrip.presentation.flightBooking.addingInfo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.Seat
import com.example.nexttrip.domain.repository.FlightRepository
import com.example.nexttrip.presentation.model.PassengerData
import com.example.nexttrip.utils.createDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddingInfoViewModel @Inject constructor(
    private val repository: FlightRepository
) : ViewModel() {
    var passengerList = MutableStateFlow<List<PassengerData>>(emptyList())
        private set

    var seatList = MutableStateFlow<List<Seat>>(emptyList())
        private set
    var selectedSeat = MutableStateFlow<List<String>>(emptyList())
        private set

    fun addPassenger(adults: String, childs: String, infants: String) = viewModelScope.launch {
        for (i in 1..adults.toInt()) {
            addPassengerData("Adult", i)
        }
        for (i in 1..childs.toInt()) {
            addPassengerData("Children", i)
        }
        for (i in 1..infants.toInt()) {
            addPassengerData("Infant", i)
        }
    }

    private fun addPassengerData(status: String, count: Int) = viewModelScope.launch {
        if (passengerList.value.none { it.status == status && it.passengerNo == count.toString() }) {
            passengerList.value += PassengerData(
                title = "MR.",
                firstName = "",
                lastName = "",
                birthDate = null,
                status = status,
                passengerNo = count.toString()
            )
        }
    }

    fun updateTitle(status: String, count: String, title: String) = viewModelScope.launch {
        passengerList.value = passengerList.value.map { passenger ->
            if (passenger.status == status && passenger.passengerNo == count) {
                passenger.copy(title = title)
            } else {
                passenger
            }
        }
    }

    fun updateFirstName(status: String, count: String, givenName: String) = viewModelScope.launch {
        passengerList.value = passengerList.value.map { passenger ->
            if (passenger.status == status && passenger.passengerNo == count) {
                passenger.copy(firstName = givenName)
            } else {
                passenger
            }
        }
    }

    fun updateLastName(status: String, count: String, surName: String) = viewModelScope.launch {
        passengerList.value = passengerList.value.map { passenger ->
            if (passenger.status == status && passenger.passengerNo == count) {
                passenger.copy(lastName = surName)
            } else {
                passenger
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateBirthDate(
        status: String,
        count: String,
        day: String,
        month: String,
        year: String
    ) = viewModelScope.launch {
        passengerList.value = passengerList.value.map { passenger ->
            if (passenger.status == status && passenger.passengerNo == count) {
                passenger.copy(birthDate = createDate(day, month, year))
            } else {
                passenger
            }
        }
    }

    fun checkCompletion(): Boolean {
        var isComplete = true
        for (passenger in passengerList.value) {
            if (passenger.firstName.isEmpty() || passenger.lastName.isEmpty() || passenger.birthDate == null) {
                isComplete = false
            }
        }
        return isComplete
    }

    fun getSeatPlans(flightNo: String) = viewModelScope.launch {
        val response = repository.getSeatPlans(flightNo)
        seatList.value = response.seatPlan.seats
    }

    private fun isSelectable(seatNo: String, classType: String): Boolean {
        val seat = seatList.value.find { it.seatNumber == seatNo }
        return seat?.status == "Available" && seat.classType == classType
    }

    fun selectSeat(seatNo: String, totalPassenger: Int, classType: String) = viewModelScope.launch {
        if (isSelectable(seatNo, classType)) {
            if (selectedSeat.value.size == totalPassenger) {
                val removedSeat = selectedSeat.value[0]
                selectedSeat.value = selectedSeat.value.drop(1) + listOf(seatNo)
                updateStatus(seatNo, "Selected")
                updateStatus(removedSeat, "Available")
            } else {
                if (selectedSeat.value.none { it == seatNo }) {
                    selectedSeat.value += listOf(seatNo)
                    updateStatus(seatNo, "Selected")
                }
            }
        }
    }

    private fun updateStatus(seatNo: String, status: String) = viewModelScope.launch {
        seatList.value = seatList.value.map { seat ->
            if (seat.seatNumber == seatNo) {
                seat.copy(status = status)
            } else {
                seat
            }
        }
    }

    fun getSeats(): String {
        var seats = selectedSeat.value[0]
        for (i in 1..<selectedSeat.value.size) {
            seats += "-${selectedSeat.value[i]}"
        }
        return seats
    }

}