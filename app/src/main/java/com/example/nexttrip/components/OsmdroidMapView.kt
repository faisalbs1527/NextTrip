package com.example.nexttrip.components

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.nexttrip.utils.MapUtils
import com.example.nexttrip.utils.MapUtils.Companion.getLocationDetails
import com.example.nexttrip.utils.RequestLocationPermission
import org.osmdroid.api.IMapController
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker

@Composable
fun OsmdroidMapView(activity: Activity, context: Context) {
    var geoPoint by remember {
        mutableStateOf(GeoPoint(23.7104, 90.40744))
    }
    RequestLocationPermission(
        onPermissionGranted = {
            Toast.makeText(context, "Permission Granted!!", Toast.LENGTH_SHORT).show()
            MapUtils.getLastUserLocation(
                onGetLastLocationSuccess = {
                    geoPoint = GeoPoint(it.first, it.second)
                    println("from last")
                },
                onGetLastLocationFailed = { exception ->
                },
                onGetLastLocationIsNull = {
                    // Attempt to get the current user location
                    MapUtils.getCurrentLocation(
                        onGetCurrentLocationSuccess = {
                            geoPoint = GeoPoint(it.first, it.second)
                            println("from current")
                        },
                        onGetCurrentLocationFailed = {
                        },
                        context = context
                    )
                },
                context = context
            )
        },
        onPermissionDenied = {
            Toast.makeText(context, "Permission Denied!!", Toast.LENGTH_SHORT).show()
        },
        onPermissionsRevoked = {
            Toast.makeText(context, "Permission Revoked!!", Toast.LENGTH_SHORT).show()
        }
    )
    println(geoPoint)
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setUseDataConnection(true)
                setMultiTouchControls(true)

                val marker = Marker(this)
                marker.position = geoPoint
                overlays.add(marker)

                val mapController: IMapController = this.controller
                mapController.setCenter(geoPoint)
                mapController.setZoom(12.0)
                val mapEventsReceiver = object : MapEventsReceiver {
                    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                        p?.let {
                            // Update GeoPoint and move the map center
                            geoPoint = it
                            getLocationDetails(it.latitude, it.longitude)
                            println(geoPoint)
                            marker.position = it
                            mapController.setCenter(it)
                        }
                        return true
                    }

                    override fun longPressHelper(p: GeoPoint?): Boolean {
                        // Handle long presses if needed
                        return false
                    }
                }
                this.overlays.add(MapEventsOverlay(mapEventsReceiver))
            }
        },
        update = { view ->
            view.controller.setCenter(geoPoint)
        }
    )
}