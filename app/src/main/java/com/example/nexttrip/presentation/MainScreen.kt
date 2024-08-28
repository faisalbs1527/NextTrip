package com.example.nexttrip.presentation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.nexttrip.components.NavBar
import com.example.nexttrip.components.NavBarItem
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.presentation.flightBooking.BookingScreen
import com.example.nexttrip.presentation.home.HomeScreen
import com.example.nexttrip.presentation.model.NavItems
import com.example.nexttrip.presentation.myBooking.MyBookingsScreen

@SuppressLint("RestrictedApi")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavController,
    selectedItem: Int
) {

    val items = listOf(
        NavItems.Home,
        NavItems.MyBooking,
        NavItems.BookNow,
        NavItems.Offer,
        NavItems.Profile,
    )
    var selectedIndex by remember {
        mutableIntStateOf(selectedItem)
    }
    Scaffold(
        bottomBar = {
            NavBar {
                items.forEach {
                    NavBarItem(
                        navItem = it,
                        isSelected = selectedIndex == it.no
                    ) {
                        selectedIndex = it.no
                        navController.navigate(it.route) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        when (navController.currentDestination?.route) {
            Screen.HomeScreen.route -> HomeScreen(
                navController = navController,
                innerPadding = innerPadding
            )

            Screen.MyBookingScreen.route -> MyBookingsScreen(
                navController = navController,
                innerPadding = innerPadding
            )

            Screen.BookingScreen.route -> BookingScreen(
                navController = navController,
                innerPadding = innerPadding
            )
        }
    }
}