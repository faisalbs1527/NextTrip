package com.example.nexttrip.presentation.hotelBooking

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.nexttrip.R
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.TicketText
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.MapUtils
import com.example.nexttrip.utils.RequestLocationPermission
import com.example.nexttrip.utils.getDateWithDay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HotelDetailsScreen(
    navController: NavController = rememberNavController(),
    viewModel: ReservationViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val checkInDate by viewModel.checkIn.collectAsState()
    val checkOutDate by viewModel.checkOut.collectAsState()
    val rooms by viewModel.roomList.collectAsState()
    val selectedHotel by viewModel.selectedHotel.collectAsState()


    LaunchedEffect(key1 = Unit) {
        viewModel.getSelectedHotelInfo()
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ButtonCustom(
                text = "Add Rooms",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                )
            ) {
                navController.navigate(Screen.AvailableRoomScreen.route)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp, bottom = 56.dp)
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "",
                        modifier = Modifier
                            .size(26.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Hotel Details",
                            fontFamily = Font_SFPro,
                            fontSize = 20.sp,
                            color = red40,
                            fontWeight = FontWeight(600)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TicketText(text = getDateWithDay(checkInDate), size = 12)

                                TicketText(text = "to", size = 12)
                                TicketText(
                                    text = getDateWithDay(checkOutDate),
                                    size = 12
                                )

                            }
                            Box(
                                modifier = Modifier
                                    .height(8.dp)
                                    .width(1.dp)
                                    .background(color = Color.Black.copy(alpha = .4f))
                            )
                            TicketText(text = rooms.size.toString() + " rooms", size = 12)
                        }
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.filter), contentDescription = "",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Black
                    )
                }
                AsyncImage(
                    model = selectedHotel.image,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(top = 8.dp),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedHotel.name,
                            fontFamily = Font_SFPro,
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600)
                        )
                        Row(
                            modifier = Modifier
                                .background(color = red40, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "${selectedHotel.discount}% OFF",
                                fontSize = 14.sp,
                                color = Color.White,
                                fontFamily = Font_SFPro,
                                fontWeight = FontWeight(500),
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = gray.copy(.4f),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "",
                                tint = red80,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "${selectedHotel.rating} Star",
                                fontSize = 14.sp,
                                color = Color.Black.copy(0.5f),
                                fontFamily = Font_SFPro
                            )
                        }
                        Row(
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
                                text = selectedHotel.location,
                                fontSize = 14.sp,
                                color = Color.Black.copy(0.5f),
                                fontFamily = Font_SFPro
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .background(color = red40, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .clickable {
                                MapUtils.showDestination(
                                    selectedHotel.latitude,
                                    selectedHotel.longitude,
                                    selectedHotel.name,
                                    context
                                )
                            }
                    ) {
                        Text(
                            text = "View On Map",
                            fontSize = 14.sp,
                            color = Color.White,
                            fontFamily = Font_SFPro,
                            fontWeight = FontWeight(500),
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    HorizontalLine()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Text(
                                text = "Check In :",
                                fontSize = 14.sp,
                                color = Color.Black.copy(0.6f),
                                fontFamily = Font_SFPro
                            )
                            Text(
                                text = selectedHotel.checkIn,
                                fontSize = 14.sp,
                                color = red80,
                                fontFamily = Font_SFPro,
                                fontWeight = FontWeight(700),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                        Row {
                            Text(
                                text = "Check Out :",
                                fontSize = 14.sp,
                                color = Color.Black.copy(0.6f),
                                fontFamily = Font_SFPro
                            )
                            Text(
                                text = selectedHotel.checkOut,
                                fontSize = 14.sp,
                                color = red80,
                                fontFamily = Font_SFPro,
                                fontWeight = FontWeight(700),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                    HorizontalLine()
                    Text(
                        text = "Hotel Description",
                        fontFamily = Font_SFPro,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                    Text(
                        text = selectedHotel.description,
                        fontSize = 14.sp,
                        color = Color.Black.copy(0.5f),
                        fontFamily = Font_SFPro,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    HorizontalLine()
                    PolicySection(
                        policyName = "Cancellation Policy",
                        rule = selectedHotel.cancellationPolicy
                    )
                    HorizontalLine()
                    PolicySection(policyName = "Pets Policy", rule = selectedHotel.pets)
                    HorizontalLine()
                    PolicySection(policyName = "Smoking", rule = selectedHotel.smoking)
                    HorizontalLine()
                    Text(
                        text = "Complimentary Services",
                        fontFamily = Font_SFPro,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                    selectedHotel.complimentaryService.forEach { text ->
                        RuleSection(ruleText = text)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun Show() {
    HotelDetailsScreen()
}

@Composable
fun PolicySection(
    policyName: String,
    rule: String
) {
    Text(
        text = policyName,
        fontFamily = Font_SFPro,
        fontSize = 20.sp,
        fontWeight = FontWeight(600),
        modifier = Modifier.padding(vertical = 6.dp)
    )
    RuleSection(ruleText = rule)
}

@Composable
fun RuleSection(
    ruleText: String
) {
    Row(
        modifier = Modifier.padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 2.dp)
                .size(6.dp)
                .background(color = red80, shape = CircleShape)
        )
        Text(
            text = ruleText,
            fontSize = 14.sp,
            color = Color.Black.copy(0.5f),
            fontFamily = Font_SFPro
        )
    }
}