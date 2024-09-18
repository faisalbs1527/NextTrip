package com.example.nexttrip.components

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.nexttrip.R
import com.example.nexttrip.components.button.TextButton
import com.example.nexttrip.presentation.model.AvailableCarData
import com.example.nexttrip.utils.Constants
import com.example.nexttrip.utils.MapUtils.Companion.getLocationDetails
import com.example.nexttrip.utils.PolyLineUtils.getRouteFromORS
import kotlinx.coroutines.launch
import org.osmdroid.api.IMapController
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

@Composable
fun OsmdroidMapView(
    context: Context,
    currGeoPoint: GeoPoint = GeoPoint(23.78238439450155, 90.40183813902087),
    showBackButton: Boolean = false,
    showRoute: Boolean = false,
    defaultScroll: Double = 0.0,
    zoomLevel: Double = 14.0,
    carLocations: List<AvailableCarData>,
    onLocationUpdate: (GeoPoint, String) -> Unit = { _, _ -> },
    onBackPress: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    var geoPoint by remember {
        mutableStateOf(currGeoPoint)
    }
    var geoLocation by remember {
        mutableStateOf("")
    }
    var routePoints by remember { mutableStateOf<List<GeoPoint>?>(emptyList()) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { ctx ->
                MapView(ctx).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    setUseDataConnection(true)
                    setMultiTouchControls(true)

                    val marker = Marker(this)
                    marker.position = geoPoint
                    overlays.add(marker)

                    val mapController: IMapController = this.controller

                    mapController.setCenter(adjustedCenter(geoPoint, this, defaultScroll))
                    mapController.setZoom(zoomLevel)
                    val mapEventsReceiver = object : MapEventsReceiver {
                        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                            p?.let {
                                // Update GeoPoint and move the map center
                                geoPoint = it
                                coroutineScope.launch {
                                    geoLocation = getLocationDetails(it.latitude, it.longitude)
                                }
                                marker.position = it
                                mapController.setCenter(it)
                                println("$geoPoint $it")
                                onLocationUpdate(geoPoint, geoLocation)
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

                view.overlays.removeAll { it is Marker && it.title == "Car Marker" }
                var carIcon = AppCompatResources.getDrawable(context, R.drawable.caricon)
                carLocations.forEach { location ->
                    val carMarker = Marker(view)
                    carIcon = carIcon?.toBitmapDrawable(20, 20, location.rotation)
                    carMarker.position = GeoPoint(location.latitude, location.longitude)
                    carMarker.icon = carIcon
                    carMarker.title = "Car Marker"
                    view.overlays.add(carMarker)

                    view.controller.setCenter(adjustedCenter(geoPoint, view, defaultScroll))

                }
                if (showRoute) {
                    val carGeoPoint = GeoPoint(carLocations[0].latitude, carLocations[0].longitude)

                    coroutineScope.launch {
                        routePoints = getRouteFromORS(Constants.ORS_KEY, currGeoPoint, carGeoPoint)
                    }
                    routePoints?.let { drawRoute(view, it) }
                }
            }
        )
        if (showBackButton) {
            TextButton(
                modifier = Modifier.padding(20.dp),
                buttonText = "Back"
            ) {
                onBackPress()
            }
        }
    }
}

fun Drawable.toBitmapDrawable(width: Int, height: Int, angle: Float): BitmapDrawable {
    val bitmap = (this as BitmapDrawable).bitmap
    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)
    val rotatedBitmap = resizedBitmap.rotateBitmap(angle)
    return BitmapDrawable(Resources.getSystem(), rotatedBitmap)
}

fun Bitmap.rotateBitmap(angle: Float): Bitmap {
    val matrix = Matrix().apply {
        postRotate(angle)
    }
    val rotatedBitmap = Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
    return rotatedBitmap
}

fun adjustedCenter(point: GeoPoint, mapView: MapView, fractionFromTop: Double): GeoPoint {

    val currentBoundingBox = mapView.projection.boundingBox
    val latSpan = currentBoundingBox.latNorth - currentBoundingBox.latSouth

    // Calculate the latitude offset to simulate the "scroll up"
    val offsetLat = (latSpan * fractionFromTop) / 2

    return GeoPoint(point.latitude - offsetLat, point.longitude)
}

fun drawRoute(mapView: MapView, routePoints: List<GeoPoint>) {

    // Add polyline (roadline)
    val polyline = Polyline().apply {
        setPoints(routePoints)
        outlinePaint.setColor(Color.RED)
    }

    mapView.overlays.add(polyline)

    mapView.invalidate()  // Redraw the map
}