package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
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
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.green90

@Composable
fun SelectBox(
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .border(width = 1.dp, color = gray.copy(.3f), shape = RoundedCornerShape(4.dp))
            .background(
                color = if (selected) green90 else Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = "",
            tint = if (selected) Color.White else Color.Transparent,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
fun SelectBoxWithText(
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SelectBox(selected = isSelected) {
            onClick()
        }
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = Font_SFPro,
            color = Color.Black.copy(.6f),
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    SelectBoxWithText( "Business"){

    }
}