package com.example.nexttrip.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red80

@Composable
fun RatingBox(
    ratingText: String
) {
    Row(
        modifier = Modifier
            .border(width = 1.dp, color = gray.copy(.4f), shape = RoundedCornerShape(4.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "",
            tint = red80,
            modifier = Modifier.size(14.dp)
        )
        Text(
            text = ratingText,
            fontSize = 12.sp,
            color = Color.Black.copy(0.5f),
            fontFamily = Font_SFPro
        )
    }
}