package com.example.nexttrip.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexttrip.R
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40

@Composable
fun HotelInfoCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.hotelimage),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, end = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    modifier = Modifier
                        .background(color = red40, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Popular",
                        color = Color.White,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(500),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    HotelInfoCard()
}