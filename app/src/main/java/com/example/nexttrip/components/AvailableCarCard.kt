package com.example.nexttrip.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R
import com.example.nexttrip.presentation.model.AvailableCarData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red10
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80

@Composable
fun AvailableCarCard(
    car: AvailableCarData,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .border(width = .5.dp, color = red40, shape = RoundedCornerShape(4.dp))
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = car.carName,
                    fontFamily = Font_SFPro,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TicketText(text = car.gearType, size = 12)
                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(1.dp)
                            .background(color = Color.Black.copy(alpha = .4f))
                    )
                    TicketText(text = "4 seats", size = 12)
                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(1.dp)
                            .background(color = Color.Black.copy(alpha = .4f))
                    )
                    TicketText(text = car.fuelType, size = 12)
                }
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "",
                        tint = red80,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "${car.routeInfo.distance}km(${car.routeInfo.duration}mins away)",
                        fontSize = 12.sp,
                        color = red80,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(600)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.carimage),
                    contentDescription = ""
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RatingBox(ratingText = "${car.rating} Star")
            Text(
                text = "BDT ${car.price}",
                fontSize = 20.sp,
                color = red80,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
//    AvailableCarCard(AvailableCarData())
}