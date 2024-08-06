package com.example.nexttrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.navigation.SetUpNavGraph
import com.example.nexttrip.ui.theme.NextTripTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NextTripTheme {
                val navController = rememberNavController()
                SetUpNavGraph(
                    navController = navController,
                    startDestination = Screen.HomeScreen.route
                )
            }
        }
    }
}