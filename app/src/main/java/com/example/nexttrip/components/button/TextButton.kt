package com.example.nexttrip.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    paddingVertical: Int = 4,
    buttonText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(color = red40, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = paddingVertical.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(600),
        )
    }
}