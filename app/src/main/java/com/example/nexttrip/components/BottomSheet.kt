package com.example.nexttrip.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_Lato
import com.example.nexttrip.ui.theme.Font_LatoBold
import com.example.nexttrip.ui.theme.black40
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetModel(
    onDismiss: () -> Unit,
    curAdult: Int,
    curChild: Int,
    curInfant: Int,
    curClass: Int,
    onDone: (Int, Int, Int, Int) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var selectedClass by remember { mutableStateOf(curClass) }
    var adult by remember { mutableStateOf(curAdult) }
    var children by remember { mutableStateOf(curChild) }
    var infant by remember { mutableStateOf(curInfant) }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Select Traveler",
                fontSize = 22.sp,
                fontFamily = Font_LatoBold,
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        HorizontalLine()
        SelectionRow(
            title = "Adult",
            limitText = "12 years and above",
            count = adult,
            category = 1
        ) {
            adult = it
        }
        HorizontalLine()
        SelectionRow(title = "Children", limitText = "2-11 years", count = children, category = 2) {
            children = it
        }
        HorizontalLine()
        SelectionRow(title = "Infant", limitText = "Below 2 years", count = infant, category = 2) {
            infant = it
        }
        HorizontalLine()

        Text(
            text = "Class",
            fontSize = 22.sp,
            fontFamily = Font_LatoBold,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(start = 12.dp)
        )
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            ClassButton(
                text = "Economy",
                textColor = if (selectedClass == 1) Color.White else Color.Black,
                containerColor = if (selectedClass == 1) black40 else black40.copy(0.1f)
            ) {
                selectedClass = 1
            }
            ClassButton(
                text = "Business",
                textColor = if (selectedClass == 2) Color.White else Color.Black,
                containerColor = if (selectedClass == 2) black40 else black40.copy(0.1f)
            ) {
                selectedClass = 2
            }
        }
        ButtonCustom(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 32.dp, start = 12.dp, end = 12.dp),
            text = "Done"
        ) {
            scope
                .launch { sheetState.hide() }
                .invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onDone(adult, children, infant, selectedClass)
                        onDismiss()
                    }
                }
        }
    }


}

@Composable
fun SelectionRow(
    title: String,
    limitText: String,
    count: Int,
    category: Int,
    onUpdate: (Int) -> Unit
) {
    var currCount by remember {
        mutableStateOf(count)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                fontSize = 18.sp,
                fontFamily = Font_LatoBold,
                fontWeight = FontWeight(400)
            )
            Text(
                text = limitText,
                fontSize = 12.sp,
                fontFamily = Font_Lato,
                fontWeight = FontWeight(400),
                color = Color.Black.copy(alpha = .6f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonRoundIcon(onClick = {
                if (category == 1 && currCount > 1) {
                    currCount--
                    onUpdate(currCount)
                } else if (category == 2 && currCount > 0) {
                    currCount--
                    onUpdate(currCount)
                }
            }, icon = Icons.Default.Remove)
            Text(
                text = currCount.toString(),
                fontSize = 18.sp,
                fontFamily = Font_Lato,
                fontWeight = FontWeight(400)
            )
            ButtonRoundIcon(onClick = {
                currCount++
                onUpdate(currCount)
            }, icon = Icons.Default.Add)
        }
    }
}


@Preview
@Composable
private fun Show() {
    var showBottomSheet by remember { mutableStateOf(true) }
    if (showBottomSheet) {
        BottomSheetModel(
            onDismiss = {
                showBottomSheet = false
            },
            curAdult = 1,
            curChild = 0,
            curInfant = 0,
            curClass = 1,
            onDone = { adultT, child, infantT, classType ->

            }
        )
    }
}