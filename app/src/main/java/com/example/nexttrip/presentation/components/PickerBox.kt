package com.example.nexttrip.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPasteGo
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_Lato

@Composable
fun PickerBox(
    modifier: Modifier = Modifier,
    modifierIcon: Modifier = Modifier,
    title: String,
    contentText: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            color = Color.Black.copy(alpha = .6f)
        )
        Row(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = Color.Black.copy(alpha = .6f),
                modifier = modifierIcon
            )
            Text(
                text = contentText,
                fontSize = 16.sp,
                fontFamily = Font_Lato,
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(start = 4.dp),
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    PickerBox(
        modifierIcon = Modifier.graphicsLayer(
            scaleX = 1f
        ),
        title = "Departure Date",
        contentText = "8 Aug,2024",
        icon = Icons.Default.ContentPasteGo,
        onClick = {}
    )
}