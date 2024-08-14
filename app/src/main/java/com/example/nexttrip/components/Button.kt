package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.Font_Lato
import com.example.nexttrip.ui.theme.Font_SFPro

@Composable
fun ButtonCustom(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF8A1C40), shape = RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun ButtonRound(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(90.dp)
            .shadow(elevation = 8.dp, shape = CircleShape)
            .background(color = Color(0xFF8A1C40), shape = CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {

        Text(
            text = "Search",
            fontFamily = Font_Lato,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight(600)
        )
    }
}

@Composable
fun ButtonRoundIcon(
    onClick: () -> Unit,
    icon: ImageVector
) {
    OutlinedIconButton(
        modifier = Modifier.size(28.dp),
        onClick = {
            onClick()
        },
        //colors = IconButtonColors(containerColor = Color.Gray.copy(.1f), contentColor = Color.Black, disabledContentColor = Color.Gray.copy(.1f), disabledContainerColor = Color.Black)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = Color.Black.copy(alpha = 0.8f)
        )
    }
}


@Composable
fun ClassButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    containerColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = containerColor, shape = RoundedCornerShape(4.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {


        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = Font_SFPro,
            color = textColor,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontWeight = FontWeight(500)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
//    ClassButton(text = "Economy", textColor = Color.White, containerColor = black40) {
//
//    }
    ButtonRoundIcon(onClick = {
    }, icon = Icons.Outlined.Add)
}