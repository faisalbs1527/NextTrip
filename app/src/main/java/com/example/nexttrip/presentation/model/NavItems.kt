package com.example.nexttrip.presentation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ControlPoint
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Redeem
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItems(
    val no: Int,
    val icon: ImageVector,
    val name: String,
    val route: String
) {
    data object Home: NavItems(
        no = 1,
        icon = Icons.Default.Home,
        name = "Home",
        route = "HomeScreen"
    )
    data object MyBooking: NavItems(
        no = 2,
        icon = Icons.Default.TravelExplore,
        name = "My Booking",
        route = "MyBookingScreen"
    )
    data object BookNow: NavItems(
        no = 3,
        icon = Icons.Default.ControlPoint,
        name = "Book Now",
        route = "BookingScreen"
    )
    data object Offer: NavItems(
        no = 4,
        icon = Icons.Default.Redeem,
        name = "Offers",
        route = "HomeScreen"
    )
    data object Profile: NavItems(
        no = 5,
        icon = Icons.Default.ManageAccounts,
        name = "Profile",
        route = "HomeScreen"
    )
}