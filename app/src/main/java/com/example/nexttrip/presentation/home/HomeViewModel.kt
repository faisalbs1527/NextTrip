package com.example.nexttrip.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexttrip.domain.model.toDestinationData
import com.example.nexttrip.domain.repository.DestinationRepository
import com.example.nexttrip.presentation.model.DestinationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DestinationRepository
) : ViewModel() {
    private val _destinationList = MutableStateFlow<List<DestinationData>>(emptyList())
    val destinationList: StateFlow<List<DestinationData>> = _destinationList

    fun getDestinations() = viewModelScope.launch {
        val response = repository.getDestinations()
        println(response)
        _destinationList.value = response.map { it.toDestinationData() }
    }
}