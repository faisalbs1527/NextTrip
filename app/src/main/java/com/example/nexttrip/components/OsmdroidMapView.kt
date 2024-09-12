package com.example.nexttrip.components

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.nexttrip.R
import com.example.nexttrip.domain.model.carBooking.LocationDhakaItem
import com.example.nexttrip.presentation.model.GeoLocation
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.MapUtils
import com.example.nexttrip.utils.MapUtils.Companion.getLocationDetails
import com.example.nexttrip.utils.carLocation
import org.osmdroid.api.IMapController
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker

@Composable
fun OsmdroidMapView(
    context: Context,
    carLocations: List<GeoLocation>,
    onLocationUpdate: (GeoPoint, String) -> Unit,
    onBackPress: () -> Unit
) {
    var geoPoint by remember {
        mutableStateOf(GeoPoint(23.78238439450155, 90.40183813902087))
    }
    var geoLocation by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = geoPoint) {
        geoLocation = getLocationDetails(geoPoint.latitude, geoPoint.longitude)
    }
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

                    var carIcon = context.getDrawable(R.drawable.caricon)
                    carLocations.forEach { location ->
                        val carMarker = Marker(this)
                        carIcon = carIcon?.toBitmapDrawable(20, 20, location.rotation)
                        carMarker.position = GeoPoint(location.latitude, location.longitude)
                        carMarker.icon = carIcon
                        overlays.add(carMarker)
                    }

                    val marker = Marker(this)
                    marker.position = geoPoint
                    overlays.add(marker)

                    val mapController: IMapController = this.controller
                    mapController.setCenter(geoPoint)
                    mapController.setZoom(14.0)
                    val mapEventsReceiver = object : MapEventsReceiver {
                        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                            p?.let {
                                // Update GeoPoint and move the map center
                                geoPoint = it
                                marker.position = it
                                mapController.setCenter(it)
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
                view.controller.setCenter(geoPoint)
            }
        )
        Row(
            modifier = Modifier
                .padding(20.dp)
                .background(color = red80, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .clickable {
                    onBackPress()
                }
        ) {
            Text(
                text = "Back",
                color = Color.White,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600),
            )
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