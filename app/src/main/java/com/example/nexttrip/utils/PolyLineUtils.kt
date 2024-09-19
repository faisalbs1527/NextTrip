package com.example.nexttrip.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import org.osmdroid.util.GeoPoint
import java.io.IOException

object PolyLineUtils {

    suspend fun getRouteFromORS(
        apiKey: String,
        startPoint: GeoPoint,
        endPoint: GeoPoint
    ): List<GeoPoint>? {
        val client = OkHttpClient()

        // JSON body for the POST request
        val requestBody = """
        {
          "coordinates": [[${startPoint.longitude}, ${startPoint.latitude}], [${endPoint.longitude}, ${endPoint.latitude}]],
          "profile": "driving-car",
          "format": "geojson"
        }
    """.trimIndent()

        // Build the request
        val request = Request.Builder()
            .url("https://api.openrouteservice.org/v2/directions/driving-car")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .addHeader("Authorization", apiKey)
            .build()

        // Make the network call in the IO thread using coroutines
        return withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val jsonResponse = response.body?.string()
                    val json = JSONObject(jsonResponse)

                    if (json.has("routes")) {
                        val routes = json.getJSONArray("routes")
                        if (routes.length() > 0) {
                            val geometry = routes.getJSONObject(0).getString("geometry")

                            // Decode the polyline string into a list of coordinates
                            val decodedPolyline = decode(geometry)

                            // Convert the list of coordinates into GeoPoint objects
                            val routePoints =
                                decodedPolyline.map { (lat, lon) -> GeoPoint(lat, lon) }

                            return@withContext routePoints
                        }
                    }
                    null
                } else {
                    null
                }
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun decode(encoded: String): List<Pair<Double, Double>> {
        val poly = mutableListOf<Pair<Double, Double>>()
        var index = 0
        val length = encoded.length
        var lat = 0
        var lng = 0
        while (index < length) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or ((b and 0x1f) shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else (result shr 1)
            lat += dlat
            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or ((b and 0x1f) shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else (result shr 1)
            lng += dlng
            poly.add(Pair(lat / 1E5, lng / 1E5))
        }
        return poly
    }
}