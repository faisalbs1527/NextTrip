package com.example.nexttrip.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.presentation.model.PassengerData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.black40
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red10
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.getDay
import com.example.nexttrip.utils.getMonth
import com.example.nexttrip.utils.getYear


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PassengerInput(
    passenger: PassengerData,
    onUpdateTitle: (String) -> Unit,
    onUpdateFirstName: (String) -> Unit,
    onUpdateLastName: (String) -> Unit,
    onUpdateDate: (String, String, String) -> Unit
) {
    var firstName by remember { mutableStateOf(passenger.firstName) }
    var lastName by remember { mutableStateOf(passenger.lastName) }
    var day by remember { mutableStateOf(getDay(passenger.birthDate)) }
    var month by remember { mutableStateOf(getMonth(passenger.birthDate)) }
    var year by remember { mutableStateOf(getYear(passenger.birthDate)) }
    var selectedTitle by remember { mutableStateOf(if (passenger.title == "MR.") 1 else if (passenger.title == "MS.") 2 else 3) }

    LaunchedEffect(day, month, year) {
        onUpdateDate(day, month, year)
    }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {

        Text(
            text = "${passenger.status} - ${passenger.passengerNo}",
            fontFamily = Font_SFPro,
            fontSize = 20.sp,
            color = red40,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(top = 8.dp)
        )

        HorizontalLine()
        Text(
            text = "Select Title",
            fontSize = 14.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(500)
        )
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            ClassButton(
                text = "MR.",
                textColor = if (selectedTitle == 1) Color.White else Color.Black,
                containerColor = if (selectedTitle == 1) black40 else black40.copy(0.1f)
            ) {
                selectedTitle = 1
                onUpdateTitle("MR.")
            }
            ClassButton(
                text = "MS.",
                textColor = if (selectedTitle == 2) Color.White else Color.Black,
                containerColor = if (selectedTitle == 2) black40 else black40.copy(0.1f)
            ) {
                selectedTitle = 2
                onUpdateTitle("MS.")
            }
            ClassButton(
                text = "MRS.",
                textColor = if (selectedTitle == 3) Color.White else Color.Black,
                containerColor = if (selectedTitle == 3) black40 else black40.copy(0.1f)
            ) {
                selectedTitle = 3
                onUpdateTitle("MRS.")
            }
        }
        Text(
            text = "Given Name/First Name",
            fontFamily = Font_SFPro,
            fontSize = 14.sp,
            fontWeight = FontWeight(400)
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            text = firstName,
            onTextChange = { firstName = it },
            placeHolderText = "Given Name",
            onFocusChange = {
                if (it == false) {
                    onUpdateFirstName(firstName)
                }
            }
        )
        Text(
            text = "Surname/Last Name",
            fontFamily = Font_SFPro,
            fontSize = 14.sp,
            fontWeight = FontWeight(400)
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            text = lastName,
            onTextChange = { lastName = it },
            placeHolderText = "SurName",
            onFocusChange = {
                if (it == false) {
                    onUpdateLastName(lastName)
                }
            }
        )
        Text(
            text = "Date of birth",
            fontFamily = Font_SFPro,
            fontSize = 14.sp,
            fontWeight = FontWeight(400)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            InputField(
                modifier = Modifier.weight(.3f),
                text = day,
                onTextChange = { day = it },
                placeHolderText = "DD",
                keyboardType = KeyboardType.Number,
                onFocusChange = {

                }
            )
            InputField(
                modifier = Modifier.weight(.3f),
                text = month,
                onTextChange = { month = it },
                placeHolderText = "MM",
                keyboardType = KeyboardType.Number,
                onFocusChange = {

                }
            )
            InputField(
                modifier = Modifier.weight(.4f),
                text = year,
                onTextChange = { year = it },
                placeHolderText = "YYYY",
                keyboardType = KeyboardType.Number,
                onFocusChange = {

                }
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 12.dp)
                .background(color = red10, shape = RoundedCornerShape(4.dp))
                .clickable {

                },
            contentAlignment = Alignment.Center
        ) {

            Row(
                modifier = Modifier.padding(start = 12.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    tint = red40,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "Add Passport details",
                    fontSize = 14.sp,
                    fontFamily = Font_SFPro,
                    color = red40,
                    fontWeight = FontWeight(500)
                )
            }

        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun Show() {
    PassengerInput(
        PassengerData(
            title = "MS.",
            firstName = "",
            lastName = "",
            birthDate = null,
            status = "Adult",
            passengerNo = "2"
        ),
        onUpdateTitle = {},
        onUpdateFirstName = {},
        onUpdateLastName = {},
        onUpdateDate = { d, m, y ->

        }
    )
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    placeHolderText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onFocusChange: (Boolean) -> Unit
) {

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = {
            Text(
                text = placeHolderText,
                fontFamily = Font_SFPro,
                fontSize = 14.sp,
                color = gray,
                fontWeight = FontWeight(400)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = gray,
            unfocusedBorderColor = gray
        ),
        modifier = modifier
            .padding(vertical = 8.dp)
            .onFocusChanged {
                onFocusChange(it.isFocused)
            },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}