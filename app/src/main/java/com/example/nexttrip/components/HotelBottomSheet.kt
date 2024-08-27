package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.nexttrip.presentation.model.RoomData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.black40
import kotlinx.coroutines.launch
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelBottomSheet(
    onDismiss: () -> Unit,
    onDone: (List<RoomData>) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var roomCount by remember {
        mutableIntStateOf(1)
    }
    var roomList by remember {
        mutableStateOf(listOf(RoomData()))
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Select Rooms",
                fontSize = 22.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
        HorizontalLine()
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            ) {
                for (room in roomList) {
                    Room(room) {
                        roomList = roomList.filter { it != room }
                        roomList = roomList.mapIndexed { index, roomData ->
                            roomData.copy(roomNo = index + 1)
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "More than 4 guests?",
                        fontSize = 16.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(300),
                        modifier = Modifier.padding(start = 12.dp, top = 8.dp)
                    )
                    AddRoomButton {
                        roomCount++
                        roomList += RoomData(roomNo = roomCount)
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
                                    onDone(roomList)
                                    onDismiss()
                                }
                            }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Show() {
    HotelBottomSheet(onDismiss = {}) {

    }
}

@Composable
fun Room(
    room: RoomData,
    onRemove: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Room ${room.roomNo}",
                fontSize = 22.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600)
            )
            ClassButton(
                text = "Remove",
                textColor = if (room.roomNo == 1) black40 else Color.White,
                containerColor = if (room.roomNo == 1) black40.copy(0.1f) else black40
            ) {
                if (room.roomNo != 1) {
                    onRemove()
                }
            }
        }
        HorizontalLine()
        GuestAdditionRow(title = "Adult", count = room.adult, limit = 4) {
            room.adult = it
        }
        HorizontalLine()
        GuestAdditionRow(
            title = "Children",
            limitText = "0-10 years",
            count = min(room.adult, room.children),
            limit = room.adult
        ) {
            room.children = it
        }
        Spacer(modifier = Modifier.padding(top = 32.dp))
    }
}

@Composable
fun GuestAdditionRow(
    title: String,
    limitText: String = "",
    count: Int,
    limit: Int,
    onUpdate: (Int) -> Unit
) {
    var currCount by remember {
        mutableIntStateOf(count)
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
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(400)
            )
            if (limitText.isNotEmpty()) {
                Text(
                    text = limitText,
                    fontSize = 12.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(400),
                    color = Color.Black.copy(alpha = .6f)
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonRoundIcon(onClick = {
                if (title == "Adult" && currCount > 1) {
                    currCount--
                    onUpdate(currCount)
                } else if (title == "Children" && currCount > 0) {
                    currCount--
                    onUpdate(currCount)
                }
            }, icon = Icons.Default.Remove)
            Text(
                text = currCount.toString(),
                fontSize = 18.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(400)
            )
            ButtonRoundIcon(onClick = {
                if (currCount < limit) {
                    currCount++
                    onUpdate(currCount)
                }
            }, icon = Icons.Default.Add)
        }
    }
}

@Composable
fun AddRoomButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = black40, shape = RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Add another room",
                fontSize = 16.sp,
                fontFamily = Font_SFPro,
                color = Color.White,
                fontWeight = FontWeight(500)
            )
        }
    }
}
