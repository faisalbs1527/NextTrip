package com.example.nexttrip.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R


@Composable
fun ServiceItem(title: String, image: Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = image), contentDescription = "",
            modifier = Modifier.size(56.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
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
    ServiceItem(title = "Flight", image = R.drawable.plane)
}
