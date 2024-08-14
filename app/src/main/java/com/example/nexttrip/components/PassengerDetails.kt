package com.example.nexttrip.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.black40
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40


@Composable
fun PassengerInput() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var selectedTitle by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Passenger 1(Adult)",
                fontFamily = Font_SFPro,
                fontSize = 20.sp,
                color = red40,
                fontWeight = FontWeight(600)
            )
        }
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
            }
            ClassButton(
                text = "MS.",
                textColor = if (selectedTitle == 2) Color.White else Color.Black,
                containerColor = if (selectedTitle == 2) black40 else black40.copy(0.1f)
            ) {
                selectedTitle = 2
            }
            ClassButton(
                text = "MRS.",
                textColor = if (selectedTitle == 3) Color.White else Color.Black,
                containerColor = if (selectedTitle == 3) black40 else black40.copy(0.1f)
            ) {
                selectedTitle = 3
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
            placeHolderText = "Given Name"
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
            placeHolderText = "SurName"
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
                keyboardType = KeyboardType.Number
            )
            InputField(
                modifier = Modifier.weight(.3f),
                text = month,
                onTextChange = { month = it },
                placeHolderText = "MM",
                keyboardType = KeyboardType.Number
            )
            InputField(
                modifier = Modifier.weight(.4f),
                text = year,
                onTextChange = { year = it },
                placeHolderText = "YYYY",
                keyboardType = KeyboardType.Number
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    PassengerInput()
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    placeHolderText: String,
    keyboardType: KeyboardType = KeyboardType.Text
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
            .padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}