package com.example.nexttrip.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WheelchairPickup
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nexttrip.presentation.model.AvailableHotelData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.blue80
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80
import java.text.NumberFormat
import java.util.Locale

@Composable
fun HotelInfoCard(
    hotel: AvailableHotelData,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = hotel.image,
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
        Text(
            text = hotel.name,
            fontFamily = Font_SFPro,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
        )
        Row(
            modifier = Modifier.padding(start = 7.dp),
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
                text = hotel.location,
                fontSize = 12.sp,
                color = Color.Black.copy(0.5f),
                fontFamily = Font_SFPro
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(color = red40, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${hotel.discount}% OFF",
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500),
                )
            }
            RatingBox(ratingText = "${hotel.rating} Star")
        }
        Column(
            modifier = Modifier.padding(start = 8.dp, top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Starts from",
                fontSize = 14.sp,
                color = Color.Black,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "BDT " + formatNumber(hotel.startPriceActual),
                    fontSize = 12.sp,
                    color = red40.copy(.8f),
                    fontFamily = Font_SFPro,
                    textDecoration = TextDecoration.LineThrough,
                )
                Text(
                    text = "BDT " + formatNumber(hotel.startPriceDiscount),
                    fontSize = 14.sp,
                    color = blue80,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(600)
                )
            }
            Text(
                text = "for 1 night,per room",
                fontSize = 12.sp,
                color = Color.Black.copy(0.5f),
                fontFamily = Font_SFPro
            )
        }
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = "",
                    tint = red80,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "24-Hour Security",
                    fontSize = 12.sp,
                    color = Color.Black.copy(0.5f),
                    fontFamily = Font_SFPro
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.WheelchairPickup,
                    contentDescription = "",
                    tint = red80,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "Disability Friendly",
                    fontSize = 12.sp,
                    color = Color.Black.copy(0.5f),
                    fontFamily = Font_SFPro
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
//    HotelInfoCard()
}

fun formatNumber(number: Int): String {
    val numberFormat = NumberFormat.getInstance(Locale.US)
    val formattedNumber = numberFormat.format(number)
    return formattedNumber
}