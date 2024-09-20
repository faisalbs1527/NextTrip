package com.example.nexttrip.components.appBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_SFPro

@Composable
fun SimpleTopBar(
    modifier: Modifier = Modifier,
    pageTitle: String,
    onBackPress: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
            contentDescription = "",
            modifier = Modifier
                .weight(.1f)
                .size(30.dp)
                .clickable {
                    onBackPress()
                }
        )
        Text(
            text = pageTitle,
            fontSize = 28.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(500),
            color = Color(0xFF8A1C40),
            modifier = Modifier.weight(.8f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(.1f))
    }
}