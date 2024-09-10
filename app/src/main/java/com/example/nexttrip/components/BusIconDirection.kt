package com.example.nexttrip.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BusIcon(
    rotation: Float = 180f
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.DirectionsBus,
            contentDescription = "",
            modifier = Modifier.size(32.dp)
                .padding(0.dp),
            tint = Color.Black.copy(.6f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.DirectionsWalk,
            contentDescription = "",
            modifier = Modifier.size(16.dp)
                .padding(0.dp)
                .graphicsLayer {
                    rotationY = rotation
                },
            tint = Color.Black.copy(.6f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    BusIcon()
}