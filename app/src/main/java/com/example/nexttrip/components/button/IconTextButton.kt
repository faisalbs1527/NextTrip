package com.example.nexttrip.components.button

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red80

@Composable
fun IconTextButton(
    icon: ImageVector,
    text: String,
    onCLick: () -> Unit
) {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Black.copy(.4f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
            .clickable {
                onCLick()
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon, contentDescription = "",
            modifier = Modifier.size(24.dp),
            tint = red80
        )
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = Font_SFPro,
            color = Color.Black
        )
    }
}