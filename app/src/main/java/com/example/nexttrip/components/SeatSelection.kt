package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.green40
import com.example.nexttrip.ui.theme.red10
import com.example.nexttrip.ui.theme.red80
import org.w3c.dom.Text

@Composable
fun SeatPlan() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 4.dp, vertical = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IdentificationBox(color = red10, text = "Available")
            IdentificationBox(color = green40, text = "Selected")
            IdentificationBox(color = red80, text = "Booked")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            HorizontalLine()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Business Class",
                    fontSize = 16.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(600)
                )
            }
            HorizontalLine()

            BusinessRow()
            BusinessRow()
            BusinessRow()
            BusinessRow()

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalLine()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Economy Class",
                    fontSize = 16.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(600)
                )
            }
            HorizontalLine()

            EconomyRow()
            EconomyRow()
            EconomyRow()
            EconomyRow()
            EconomyRow()
            EconomyRow()
            EconomyRow()
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}


@Composable
fun BusinessRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        Row(
            modifier = Modifier.weight(.42f)
        ) {
            SeatBox(
                seatNo = "1A",
                modifier = Modifier.weight(.45f)
            )
            Spacer(modifier = Modifier.weight(.1f))
            SeatBox(
                seatNo = "1B",
                modifier = Modifier.weight(.45f)
            )
        }
        Spacer(modifier = Modifier.weight(.16f))
        Row(
            modifier = Modifier.weight(.42f)
        ) {
            SeatBox(
                seatNo = "1C",
                modifier = Modifier.weight(.45f)
            )
            Spacer(modifier = Modifier.weight(.1f))
            SeatBox(
                seatNo = "1D",
                modifier = Modifier.weight(.45f)
            )
        }
    }
}

@Composable
fun EconomyRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier.weight(.42f)
        ) {
            SeatBox(
                seatNo = "5A",
                modifier = Modifier.weight(.3f)
            )
            Spacer(modifier = Modifier.weight(.05f))
            SeatBox(
                seatNo = "5B",
                modifier = Modifier.weight(.3f)
            )
            Spacer(modifier = Modifier.weight(.05f))
            SeatBox(
                seatNo = "5C",
                modifier = Modifier.weight(.3f)
            )
        }
        Spacer(modifier = Modifier.weight(.16f))
        Row(
            modifier = Modifier.weight(.42f)
        ) {
            SeatBox(
                seatNo = "5D",
                modifier = Modifier.weight(.3f)
            )
            Spacer(modifier = Modifier.weight(.05f))
            SeatBox(
                seatNo = "5E",
                modifier = Modifier.weight(.3f)
            )
            Spacer(modifier = Modifier.weight(.05f))
            SeatBox(
                seatNo = "5F",
                modifier = Modifier.weight(.3f)
            )
        }
    }
}

@Composable
fun SeatBox(
    modifier: Modifier = Modifier,
    color: Color = red10,
    seatNo: String = "5B",
    textColor: Color = red80
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .background(color = color, shape = RoundedCornerShape(4.dp))
            .clickable {

            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seatNo,
            color = textColor,
            fontSize = 16.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(500)
        )
    }
}

@Composable
fun IdentificationBox(
    color: Color,
    text: String
) {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(color = color, shape = RoundedCornerShape(4.dp))
        )
        Text(
            text = text,
            fontSize = 12.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(600)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    SeatPlan()
}