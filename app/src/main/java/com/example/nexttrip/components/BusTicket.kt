package com.example.nexttrip.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AirlineSeatReclineNormal
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R
import com.example.nexttrip.presentation.model.AvailableBusData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.currentDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BusTicket(
    busData: AvailableBusData,
    fromLoc: String,
    toLoc: String,
    travelDate: String,
    seats: String,
    totalFare: String,
    barcodeWidth: Int = 800
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .background(color = Color.White)
                .padding(start = 20.dp, end = 20.dp)
                .verticalScroll(
                    state = rememberScrollState()
                ),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = busData.companyName,
                fontSize = 24.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(400),
                modifier = Modifier.padding(top = 16.dp, bottom = 2.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.busimage),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
                    .height(140.dp)
                    .background(color = gray.copy(.1f)),
                contentScale = ContentScale.FillBounds
            )


            BusTravelLocationSection(
                departure = fromLoc,
                arrival = toLoc,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Spacer(modifier = Modifier.weight(.05f))
                DateTimeBox(
                    modifier = Modifier.weight(.42f),
                    icon = Icons.Default.CalendarMonth,
                    title = "Date",
                    content = travelDate
                )
                Spacer(modifier = Modifier.weight(.06f))
                DateTimeBox(
                    modifier = Modifier.weight(.42f),
                    icon = Icons.Default.AccessTime,
                    title = "Time",
                    content = "${busData.busSchedule.departureTime}-${busData.busSchedule.arrivalTime}"
                )
                Spacer(modifier = Modifier.weight(.05f))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoColumn(title = "Bus No", text = busData.busNo)
                InfoColumn(title = "Ticket No", text = "42WLd94")
                InfoColumn(title = "Coach", text = busData.coachType)
            }
            HorizontalLine()
            IconInfoRow(
                modifier = Modifier.padding(vertical = 8.dp),
                title = "Seats",
                text = seats,
                icon = Icons.Default.AirlineSeatReclineNormal
            )
            IconInfoRow(
                modifier = Modifier.padding(vertical = 4.dp),
                title = "Total Fare",
                text = "BDT $totalFare",
                icon = Icons.Default.Paid
            )
            IconInfoRow(
                modifier = Modifier.padding(vertical = 4.dp),
                title = "Booking Date",
                text = currentDate,
                icon = Icons.Default.CalendarMonth
            )
            HorizontalLine()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 20.dp)
            ) {
                val bitmap = generateBarcode("42WLd94", barcodeWidth, (barcodeWidth * 0.22).toInt())
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Barcode",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun Show() {
//    BusTicket()
}