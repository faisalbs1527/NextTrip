package com.example.nexttrip.components.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.presentation.model.ServiceItemData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red10
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.itemsList

@Composable
fun SelectionBar(
    selectedId: Int,
    items: List<ServiceItemData> = itemsList,
    onSelect: (Int) -> Unit
) {

    var selectedItem by remember { mutableIntStateOf(selectedId) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            SelectionItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = item.title,
                selected = selectedItem == item.id
            ) {
                selectedItem = item.id
                onSelect(selectedItem)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    SelectionBar(
        selectedId = 2
    ) {

    }
}

@Composable
fun SelectionItem(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .background(
                color = if (selected) red40 else red10,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(8.dp),
            color = if (selected) Color.White else red80
        )
    }
}