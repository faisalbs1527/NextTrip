package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.RoomPreferences
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nexttrip.presentation.model.AvailableRoomInfo
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.black40
import com.example.nexttrip.ui.theme.blue80
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40

@Composable
fun RoomInfoCard(
    room: AvailableRoomInfo,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(.35f)
                    .height(200.dp)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(4.dp))
            ) {
                AsyncImage(
                    model = room.image,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .weight(.65f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = room.roomType,
                    fontFamily = Font_SFPro,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                )
                InfoSectionWithIcon(icon = Icons.Default.Bed, text = room.bedType)
                InfoSectionWithIcon(
                    icon = Icons.Default.People,
                    text = "${room.capacity} Adults, ${room.capacity} Child"
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "",
                        tint = gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Bed Type might change depending on availability",
                        fontSize = 12.sp,
                        color = Color.Black.copy(0.5f),
                        fontFamily = Font_SFPro,
                        maxLines = 2
                    )
                }
                InfoSectionWithIcon(
                    icon = Icons.Default.RoomPreferences,
                    text = "${room.noOfBeds} Beds"
                )
                InfoSectionWithIcon(icon = Icons.Default.AcUnit, text = room.acStatus)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.padding(top = 12.dp),
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
                        text = "BDT ${room.actualPrice}",
                        fontSize = 12.sp,
                        color = red40.copy(.8f),
                        fontFamily = Font_SFPro,
                        textDecoration = TextDecoration.LineThrough,
                    )
                    Text(
                        text = "BDT ${room.discountPrice}",
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
                Row(
                    modifier = Modifier
                        .background(color = black40, shape = RoundedCornerShape(2.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "${room.discount}% OFF",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(500),
                    )
                }
            }
            Row(
                modifier = Modifier
                    .background(
                        color = if (isSelected) black40.copy(.2f) else red40,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable {
                        onClick()
                    }
            ) {
                Text(
                    text = "SELECT",
                    fontSize = 14.sp,
                    color = if (isSelected) Color.Black else Color.White,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    RoomInfoCard(AvailableRoomInfo(), false) {}
}

@Composable
fun InfoSectionWithIcon(
    icon: ImageVector,
    text: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = gray,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Black.copy(0.5f),
            fontFamily = Font_SFPro
        )
    }
}