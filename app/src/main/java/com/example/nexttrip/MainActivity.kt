package com.example.nexttrip

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.navigation.SetUpNavGraph
import com.example.nexttrip.ui.theme.NextTripTheme
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load osmdroid configuration
        Configuration.getInstance().load(this, getSharedPreferences("osm_prefs", MODE_PRIVATE))
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