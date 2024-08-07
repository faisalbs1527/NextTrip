package com.example.nexttrip.presentation.booking

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.NextTripTheme
import com.example.nexttrip.ui.theme.red40

data class Location(
    val name: String,
    val city: String,
    val country: String
)

val sampleLocations = listOf(
    Location("John F. Kennedy International Airport", "New York", "USA"),
    Location("Los Angeles International Airport", "Los Angeles", "USA"),
    Location("Dubai International Airport", "Dubai", "UAE"),
    Location("Heathrow Airport", "London", "UK"),
    Location("Sydney Kingsford Smith Airport", "Sydney", "Australia")
)


@Composable
fun SearchScreen(
    title: String,
    focusTarget: Int,
) {

    val focusRequesterFrom = remember {
        FocusRequester()
    }
    val focusRequesterTo = remember {
        FocusRequester()
    }

    LaunchedEffect(focusTarget) {
        when (focusTarget) {
            1 -> focusRequesterFrom.requestFocus()
            2 -> focusRequesterTo.requestFocus()
        }
    }
    var titleText by remember { mutableStateOf(title) }
    var fromFocus by remember { mutableStateOf(false) }
    var toFocus by remember { mutableStateOf(false) }
    var fromQuery by remember { mutableStateOf("") }
    var toQuery by remember { mutableStateOf("") }

    val filteredLocations = sampleLocations.filter { location ->
        location.name.contains(if (fromFocus) fromQuery else toQuery, ignoreCase = true) ||
                location.city.contains(if (fromFocus) fromQuery else toQuery, ignoreCase = true) ||
                location.country.contains(if (fromFocus) fromQuery else toQuery, ignoreCase = true)
    }

    Log.d("FilterDebug", "Filtered locations: $filteredLocations")

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "",
                    modifier = Modifier.size(36.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = titleText,
                        fontSize = 24.sp,
                        color = Color(0xFF8A1C40),
                        modifier = Modifier.padding(end = 12.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                SearchBar(
                    query = fromQuery,
                    onQueryChange = { fromQuery = it },
                    label = "From",
                    isFocused = fromFocus,
                    onFocusChange = {
                        fromFocus = it
                        titleText = "From Where?"
                    },
                    focusRequester = focusRequesterFrom
                )
                SearchBar(
                    query = toQuery,
                    onQueryChange = { toQuery = it },
                    label = "To",
                    isFocused = toFocus,
                    onFocusChange = {
                        toFocus = it
                        titleText = "Where to?"
                    },
                    focusRequester = focusRequesterTo
                )
                Spacer(modifier = Modifier.height(16.dp))
                LocationList(locations = filteredLocations)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    label: String,
    isFocused: Boolean,
    onFocusChange: (Boolean) -> Unit,
    focusRequester: FocusRequester
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text(label) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (isFocused) red40 else Color.Black.copy(0.6f),
            unfocusedBorderColor = Color.Black.copy(0.6f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged {
                onFocusChange(it.isFocused)
            }
    )
}

@Composable
fun LocationList(locations: List<Location>) {
    LazyColumn {
        items(locations) { location ->
            LocationItem(location)
        }
    }
}

@Composable
fun LocationItem(location: Location) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "${location.city}, ${location.country}",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight(500)
            )
            Text(text = location.name,
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.6f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    NextTripTheme {
        SearchScreen(
            title = "From Where?",
            focusTarget = 1
        )
    }
}
