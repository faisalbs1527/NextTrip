package com.example.nexttrip.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R

@Composable
fun PlanCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(380.dp)
            .height(260.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.dubai),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 1f)
                            )
                        )
                    )
                }
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 12.dp, bottom = 12.dp), verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Dubai City",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight(700)
            )
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextWithIcon(
                    icon = painterResource(id = R.drawable.departure),
                    text = "16 Aug,2024"
                )
                TextWithIcon(
                    icon = painterResource(id = R.drawable.seat),
                    text = "Business Class"
                )
            }
            Row(
                modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "From",
                    color = Color.White,
                )
                Text(
                    text = "$3100.00",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight(700)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    PlanCard()
}

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon, contentDescription = "", tint = Color.White,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = text,
            color = Color.White
        )
    }
}