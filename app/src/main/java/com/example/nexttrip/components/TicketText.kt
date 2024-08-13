package com.example.nexttrip.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_Lato
import com.example.nexttrip.ui.theme.Font_SFPro

@Composable
fun TicketText(text: String, size: Int) {
    Text(
        text = text,
        fontSize = size.sp,
        fontFamily = Font_SFPro,
        fontWeight = FontWeight(400),
        color = Color.Black.copy(alpha = .6f)
    )
}