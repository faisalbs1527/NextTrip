package com.example.nexttrip.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_Lato
import com.example.nexttrip.ui.theme.Font_LatoBold


@Composable
private fun ScheduleContent(
    startTime: String,
    endTime: String,
    startLoc: String,
    endLoc: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = startTime,
                fontSize = 20.sp,
                fontFamily = Font_LatoBold,
                fontWeight = FontWeight(400)
            )
            Text(
                text =startLoc ,
                fontSize = 12.sp,
                fontFamily = Font_Lato,
                fontWeight = FontWeight(400),
                color = Color.Black.copy(alpha = .6f)
            )
        }
        OutlinedIconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ScheduleContent(
        startTime = "12:25",
        endTime = "13:10",
        startLoc = "DHA",
        endLoc = "CXB"
    )
}