package com.example.nexttrip.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconRoundButton(
    imageVector: ImageVector,
    borderWidth: Dp,
    borderColor: Color,
    iconColor: Color,
    size: Dp
) {
    Box(
        modifier = Modifier
            .background(color = Color.Transparent, shape = CircleShape)
            .border(width = borderWidth, color = borderColor, shape = CircleShape)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Box Icon",
            tint = iconColor,
            modifier = Modifier.size(size)
        )
    }
}