package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.utils.cityList

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSelect: (String) -> Unit,
    placeholder: String,
    cityList: List<String>,
    iconRotation: Float
) {
    var isExpanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(.1f),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BusIcon(iconRotation)
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(36.dp)
                        .width(1.dp)
                        .background(color = Color.Black.copy(alpha = .2f))
                )
                BasicTextField(
                    value = value,
                    onValueChange = {
                        onValueChange(it)
                        isExpanded = true
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                        .focusRequester(focusRequester)
                        .clickable {
                            focusRequester.requestFocus()
                            isExpanded = true
                        },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black.copy(alpha = .6f),
                        fontFamily = Font_SFPro
                    ),
                    decorationBox = {
                        Box(
                            contentAlignment = Alignment.CenterStart
                        ) {

                            if (value.isBlank()) {
                                Text(
                                    text = placeholder,
                                    style = TextStyle(
                                        color = Color.Black.copy(.6f),
                                        fontSize = 16.sp,
                                        fontFamily = Font_SFPro
                                    )
                                )
                            }
                        }
                        it()
                    }
                )

            }
        }
        if (value.isNotEmpty() && isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 1.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
                    .padding(vertical = 8.dp)
            ) {
                cityList.forEach { city ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "",
                            tint = Color.Black.copy(.6f)
                        )
                        Text(
                            text = city,
                            modifier = Modifier
                                .clickable {
                                    onSelect(city)
                                    isExpanded = false
                                    focusManager.clearFocus()
                                }
                                .padding(8.dp)
                                .fillMaxWidth(),
                            fontSize = 16.sp,
                            fontFamily = Font_SFPro,
                            color = Color.Black.copy(.6f)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowCustomTextField() {

    var text by remember { mutableStateOf("") }
    var filteredCities by remember { mutableStateOf(cityList) }

    fun updateSuggestions(query: String) {
        filteredCities = cityList.filter { it.contains(query, ignoreCase = true) }
    }
    CustomTextField(
        value = text,
        placeholder = "From",
        onValueChange = {
            text = it
            updateSuggestions(it)
        },
        cityList = filteredCities,
        onSelect = {
            text = it
            filteredCities = cityList
        },
        iconRotation = 0f
    )
}
