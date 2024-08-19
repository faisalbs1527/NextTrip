package com.example.nexttrip.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nexttrip.R

@Composable
fun PaymentMethod() {
    val BkashPayment = remember {
        mutableStateOf(true)
    }
    val CardPayment = remember {
        mutableStateOf(false)
    }
    Column {
        OutlinedCard(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){
            Row (Modifier.padding(start = 12.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                CustomCheckBox(
                    isChecked = CardPayment.value,
                    radius = 16,
                    height = 28,
                    width = 28,
                    onCheckChange = {
                        CardPayment.value = true
                        BkashPayment.value = false
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.credit_card),
                    contentDescription = "credit",
                    modifier = Modifier
                        .size(width = 45.dp, height = 28.dp),
                    contentScale = ContentScale.Crop
                )
                Text(text = "Credit Card", modifier = Modifier.padding(start = 8.dp))
            }
        }
        OutlinedCard(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){
            Row (Modifier.padding(start = 12.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                CustomCheckBox(
                    isChecked = BkashPayment.value,
                    radius = 16,
                    height = 28,
                    width = 28,
                    onCheckChange = {
                        BkashPayment.value = true
                        CardPayment.value = false
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.bkash),
                    contentDescription = "bkash",
                    modifier = Modifier
                        .size(width = 45.dp, height = 28.dp),
                    contentScale = ContentScale.Fit
                )
                Text(text = "Bkash", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PaymentMethodPreview() {
    Surface {
        PaymentMethod()
    }
}
@Composable
fun CustomCheckBox(
    isChecked : Boolean,
    radius : Int,
    height : Int = 20,
    width : Int = 20,
    onCheckChange : (Boolean) -> Unit = {}) {
    Box(
        Modifier
            .size(width = height.dp, height = width.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFD5D5DA),
                shape = RoundedCornerShape(radius.dp)
            )
            .clickable {
                onCheckChange(!isChecked)
            }
    ) {
        if(isChecked){
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Box Icon" ,
                Modifier.align(Alignment.Center)
            )
        }
    }
}