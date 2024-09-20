package com.example.nexttrip.presentation.flightBooking.addingInfo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexttrip.domain.model.Seat
import com.example.nexttrip.domain.repository.FlightRepository
import com.example.nexttrip.presentation.model.PassengerData
import com.example.nexttrip.utils.createDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
    var seatListDeparture = MutableStateFlow<List<Seat>>(emptyList())
        private set
    var seatListReturn = MutableStateFlow<List<Seat>>(emptyList())
        private set
    var selectedSeatDeparture = MutableStateFlow<List<String>>(emptyList())
        private set
    var selectedSeatReturn = MutableStateFlow<List<String>>(emptyList())
        private set
    var travelStatus = MutableStateFlow<Int>(1)

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

    fun getSeatPlans(flightNoDeparture: String, flightNoReturn: String, roundWay: Boolean) =
        viewModelScope.launch {
            val response1 = repository.getSeatPlans(flightNoDeparture)
            seatListDeparture.value = response1.seatPlan.seats
            if (roundWay) {
                val response2 = repository.getSeatPlans(flightNoReturn)
                seatListReturn.value = response2.seatPlan.seats
            }
            updateSeatList()
        }

    private fun isSelectable(seatNo: String, classType: String): Boolean {
        if (travelStatus.value == 1) {
            val seat = seatListDeparture.value.find { it.seatNumber == seatNo }
            return seat?.status == "Available" && seat.classType == classType
        } else {
            val seat = seatListReturn.value.find { it.seatNumber == seatNo }
            return seat?.status == "Available" && seat.classType == classType
        }
    }

    fun selectSeat(seatNo: String, totalPassenger: Int, classType: String) = viewModelScope.launch {
        if (isSelectable(seatNo, classType)) {
            if (travelStatus.value == 1) {
                if (selectedSeatDeparture.value.size == totalPassenger) {
                    val removedSeat = selectedSeatDeparture.value[0]
                    selectedSeatDeparture.value =
                        selectedSeatDeparture.value.drop(1) + listOf(seatNo)
                    updateStatus(seatNo, "Selected")
                    updateStatus(removedSeat, "Available")
                } else {
                    if (selectedSeatDeparture.value.none { it == seatNo }) {
                        selectedSeatDeparture.value += listOf(seatNo)
                        updateStatus(seatNo, "Selected")
                    }
                }
            } else {
                if (selectedSeatReturn.value.size == totalPassenger) {
                    val removedSeat = selectedSeatReturn.value[0]
                    selectedSeatReturn.value =
                        selectedSeatReturn.value.drop(1) + listOf(seatNo)
                    updateStatus(seatNo, "Selected")
                    updateStatus(removedSeat, "Available")
                } else {
                    if (selectedSeatReturn.value.none { it == seatNo }) {
                        selectedSeatReturn.value += listOf(seatNo)
                        updateStatus(seatNo, "Selected")
                    }
                }
            }
        }
    }

    private fun updateStatus(seatNo: String, status: String) = viewModelScope.launch {
        if (travelStatus.value == 1) {
            seatListDeparture.value = seatListDeparture.value.map { seat ->
                if (seat.seatNumber == seatNo) {
                    seat.copy(status = status)
                } else {
                    seat
                }
            }
            seatList.value = seatListDeparture.value
        } else {
            seatListReturn.value = seatListReturn.value.map { seat ->
                if (seat.seatNumber == seatNo) {
                    seat.copy(status = status)
                } else {
                    seat
                }
            }
            seatList.value = seatListReturn.value
        }
    }

    fun updateTravelStatus(status: Int) = viewModelScope.launch {
        travelStatus.value = status
        updateSeatList()
    }

    private fun updateSeatList() = viewModelScope.launch {
        if (travelStatus.value == 1) {
            seatList.value = seatListDeparture.value
        } else {
            seatList.value = seatListReturn.value
        }
    }

    fun getSeats(): String {
        var seats: String = ""
        if (travelStatus.value == 1) {
            seats = selectedSeatDeparture.value[0]
            for (i in 1..<selectedSeatDeparture.value.size) {
                seats += "-${selectedSeatDeparture.value[i]}"
            }
        } else {
            seats = selectedSeatReturn.value[0]
            for (i in 1..<selectedSeatReturn.value.size) {
                seats += "-${selectedSeatReturn.value[i]}"
            }
        }
        return seats
    }

}