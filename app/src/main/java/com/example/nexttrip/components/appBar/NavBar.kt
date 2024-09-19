package com.example.nexttrip.components.appBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.nexttrip.presentation.model.NavItems
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red80
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavBar(
    content: @Composable RowScope.() -> Unit
) {
    Surface {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Composable
fun NavBarItem(
    navItem: NavItems = NavItems.Home,
    isSelected: Boolean = true,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = navItem.icon,
            contentDescription = "",
            tint = if (isSelected) red80 else gray
        )
        Text(
            text = navItem.name,
            fontSize = 10.sp,
            color = if (isSelected) red80 else gray,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(500)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    val items = listOf(
        NavItems.Home,
        NavItems.MyBooking,
        NavItems.BookNow,
        NavItems.Offer,
        NavItems.Profile
    )
    var selectedItem by remember {
        mutableIntStateOf(1)
    }
    NavBar {
        items.forEach {
            NavBarItem(
                navItem = it,
                isSelected = selectedItem == it.no
            ) {
                selectedItem = it.no
            }
        }
    }
}