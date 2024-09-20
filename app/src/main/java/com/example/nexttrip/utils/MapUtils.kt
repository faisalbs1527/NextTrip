package com.example.nexttrip.utils

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import com.example.nexttrip.presentation.model.RouteInfo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import kotlin.math.ceil

class MapUtils {

    companion object {

        private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

        @SuppressLint("MissingPermission")
        fun getLastUserLocation(
            onGetLastLocationSuccess: (Pair<Double, Double>) -> Unit,
            onGetLastLocationFailed: (Exception) -> Unit,
            onGetLastLocationIsNull: () -> Unit,
            context: Context
        ) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
            // Check if location permissions are granted
            if (areLocationPermissionsGranted(context)) {
                // Retrieve the last known location
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            // If location is not null, invoke the success callback with latitude and longitude
                            onGetLastLocationSuccess(Pair(location.latitude, location.longitude))
                        } else {
                            onGetLastLocationIsNull()
                        }
                    }
                    .addOnFailureListener { exception ->
                        // If an error occurs, invoke the failure callback with the exception
                        onGetLastLocationFailed(exception)
                    }
            }
        }

        @SuppressLint("MissingPermission")
        fun getCurrentLocation(
            onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
            onGetCurrentLocationFailed: (Exception) -> Unit,
            priority: Boolean = true,
            context: Context
        ) {
            // Determine the accuracy priority based on the 'priority' parameter
            val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
            else Priority.PRIORITY_BALANCED_POWER_ACCURACY

            // Check if location permissions are granted
            if (areLocationPermissionsGranted(context)) {
                // Retrieve the current location asynchronously
                fusedLocationProviderClient.getCurrentLocation(
                    accuracy, CancellationTokenSource().token,
                ).addOnSuccessListener { location ->
                    location?.let {
                        // If location is not null, invoke the success callback with latitude and longitude
                        onGetCurrentLocationSuccess(Pair(it.latitude, it.longitude))
                    }?.run {
                        //Location null do something
                    }
                }.addOnFailureListener { exception ->
                    // If an error occurs, invoke the failure callback with the exception
                    onGetCurrentLocationFailed(exception)
                }
            }
        }

        suspend fun getDistanceAndDuration(
            lat1: Double,
            lon1: Double,
            lat2: Double,
            lon2: Double
        ): RouteInfo {
            val client = OkHttpClient()
            val url =
                "https://router.project-osrm.org/route/v1/driving/$lon1,$lat1;$lon2,$lat2?overview=false"

            return withContext(Dispatchers.IO) {
                val request = Request.Builder()
                    .url(url)
                    .build()

                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val responseData = response.body?.string()
                    responseData?.let {
                        val json = JSONObject(it)
                        val routes = json.getJSONArray("routes")
                        if (routes.length() > 0) {
                            val route = routes.getJSONObject(0)
                            val distance =
                                String.format("%.2f", route.getDouble("distance") / 1000)
                                    .toDouble() // Convert meters to kilometers
                            val duration =
                                ceil(route.getDouble("duration") / 60).toInt() // Convert seconds to minutes
                            return@withContext RouteInfo(distance, duration)
                        }
                    }
                    return@withContext RouteInfo()
                }
            }
        }

        fun showDestination(
            destinationLat: Double,
            destinationLng: Double,
            locationName: String?,
            context: Context
        ) {
            try {
                // Create a URI with Google Maps URL to show the destination location
                val uri: Uri = if (locationName.isNullOrEmpty()) {
                    Uri.parse("geo:$destinationLat,$destinationLng")
                } else {
                    // Format to include a label at the destination location
                    Uri.parse("geo:$destinationLat,$destinationLng?q=$destinationLat,$destinationLng($locationName)")
                }

                // Initialize an intent with ACTION_VIEW
                val intent = Intent(Intent.ACTION_VIEW, uri)

                // Set the package to Google Maps
                intent.setPackage("com.google.android.apps.maps")

                // Set flags
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                // Start the activity
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // If Google Maps is not installed, redirect to Google Play to install it
                val playStoreUri: Uri =
                    Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
                val playStoreIntent = Intent(Intent.ACTION_VIEW, playStoreUri)
                playStoreIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(playStoreIntent)
            }
        }

        fun drawTrack(
            sourceLat: Double,
            sourceLng: Double,
            destinationLat: Double,
            destinationLng: Double,
            context: Context
        ) {
            try {
                // create a uri
                val uri: Uri =
                    Uri.parse("https://www.google.co.in/maps/dir/?api=1&origin=$sourceLat,$sourceLng&destination=$destinationLat,$destinationLng")
                // initializing a intent with action view.
                val i = Intent(Intent.ACTION_VIEW, uri)
                // below line is to set maps package name
                i.setPackage("com.google.android.apps.maps")
                // below line is to set flags
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                // start activity
                context.startActivity(i)
            } catch (e: ActivityNotFoundException) {
                // when the google maps is not installed on users device
                // we will redirect our user to google play to download google maps.
                val uri: Uri =
                    Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
                // initializing intent with action view.
                val i = Intent(Intent.ACTION_VIEW, uri)
                // set flags
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                // to start activity
                context.startActivity(i)
            }
        }

        private fun areLocationPermissionsGranted(context: Context): Boolean {
            return (ActivityCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(
                        context, android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED)
        }

        suspend fun getLocationDetails(lat: Double, lng: Double): String {
            val client = OkHttpClient()
            val url =
                "https://nominatim.openstreetmap.org/reverse?format=json&lat=$lat&lon=$lng&addressdetails=1"


            return withContext(Dispatchers.IO) {
                try {
                    val request =
                        Request.Builder().url(url).addHeader("Accept-Language", "en").build()
                    val response = client.newCall(request).execute()
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val jsonData = response.body?.string()
                    val jsonObject = JSONObject(jsonData)
                    val address = jsonObject.getJSONObject("address")
                    val formattedAddress = address.optString("road", "") + ", " +
                            address.optString("suburb", "") + ", " +
                            address.optString("city", "") + ", " +
                            address.optString("state", "")

                    formattedAddress

                } catch (e: IOException) {
                    e.printStackTrace()
                    ""
                }
            }
        }

        fun resolveShortenedUrl(shortUrl: String, callback: (String) -> Unit) {
            val client = OkHttpClient()
            val request = Request.Builder().url(shortUrl).build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if (response.isSuccessful) {
                        val resolvedUrl = response.request.url.toString()
                        callback(resolvedUrl)
                    }
                }
            })
        }

        fun extractLatLngFromUrl(url: String): Pair<Double, Double>? {
            val regex = Regex("""@(-?\d+\.\d+),(-?\d+\.\d+)""")
            val matchResult = regex.find(url) ?: return null
            val (lat, lng) = matchResult.destructured
            return Pair(lat.toDouble(), lng.toDouble())
        }
    }
}