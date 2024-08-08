package com.example.nexttrip.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_Lato
import com.example.nexttrip.ui.theme.Font_LatoBold
import com.example.nexttrip.ui.theme.NextTripTheme
import com.example.nexttrip.ui.theme.red40

@Composable
fun TicketType(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(8.dp)
            .background(
                color = if(selected) red40 else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = Font_LatoBold,
            modifier = Modifier.padding(8.dp),
            color = if(selected)Color.White else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    NextTripTheme {
        TicketType(
            modifier = Modifier,
            text = "One Way",
            selected = true,
            {}
        )
    }
}