package com.example.nexttrip.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.example.nexttrip.ui.theme.red40

@Composable
fun AddReturn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "+ ADD RETURN DATE",
            fontSize = 13.sp,
            fontFamily = Font_Lato,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(top = 4.dp),
            color = red40.copy(alpha = 0.9f)
        )
        Text(
            text = "Save more on return trips!!",
            fontSize = 12.sp,
            fontFamily = Font_Lato,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(top = 2.dp),
            color = Color.Black.copy(alpha = 0.6f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    AddReturn(){

    }
}