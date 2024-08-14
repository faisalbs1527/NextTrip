package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexttrip.ui.theme.red10

@Composable
fun SeatPlan() {

}


@Composable
fun SeatBox(
    color: Color
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(color = color, shape = RoundedCornerShape(4.dp))
    )
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    SeatBox(color = red10)
}