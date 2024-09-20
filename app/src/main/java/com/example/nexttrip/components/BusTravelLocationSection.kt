package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40

@Composable
fun BusTravelLocationSection(
    departure: String,
    arrival: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(.3f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = departure,
                    fontSize = 18.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500)
                )
            }

            Column(
                modifier = Modifier.weight(.4f),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(.4f)
                            .padding(vertical = 8.dp)
                            .height(1.dp)
                            .background(color = Color.Black.copy(alpha = .2f))
                    )
                    Icon(
                        imageVector = Icons.Default.DirectionsBus,
                        contentDescription = "",
                        tint = red40,
                        modifier = Modifier
                            .weight(.2f)
                            .graphicsLayer { rotationZ = 0f }
                    )
                    ForwardArrow(modifier = Modifier.weight(.4f))
                }
            }

            Column(
                modifier = Modifier.weight(.3f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = arrival,
                    fontSize = 18.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500)
                )
            }

        }
    }
}