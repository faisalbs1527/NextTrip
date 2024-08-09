package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R


@Composable
fun ServiceItem(
    title: String,
    image: Int,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .background(
                    color = Color(0xFF8A1C40).copy(alpha = 0.15f),
                    shape = RoundedCornerShape(16.dp)
                )
                .clickable {
                    onClick()
                }
        ) {
            Icon(
                painter = painterResource(id = image), contentDescription = "",
                modifier = Modifier
                    .padding(4.dp)
                    .size(42.dp),
                tint = Color(0xFF8A1C40)
            )
        }
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp,
                color = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ServiceItem(title = "Flight", image = R.drawable.bus,{})
}
