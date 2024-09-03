package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80

@Composable
fun PaymentSection(
    actualPayment: String = "$500.00",
    payment: String,
    isIncludeDiscount: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Payment Methods",
            fontSize = 18.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
        )
        PaymentMethod()
        Text(
            text = "Price Details",
            fontSize = 18.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Total Payment",
                    fontSize = 14.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500),
                    color = gray
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isIncludeDiscount) {
                        Text(
                            text = actualPayment,
                            fontSize = 24.sp,
                            color = gray.copy(.8f),
                            fontFamily = Font_SFPro,
                            textDecoration = TextDecoration.LineThrough,
                        )
                    }
                    Text(
                        text = payment,
                        fontSize = 24.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(600),
                        color = red80
                    )
                }
            }
            HorizontalLine()
            Column(
                modifier = Modifier.padding(top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Promo Code",
                    fontSize = 14.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500),
                    color = gray
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.award_star),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp),
                            tint = red80
                        )
                        Text(
                            text = "Enter code",
                            fontFamily = Font_SFPro,
                            fontSize = 14.sp,
                            color = gray,
                            fontWeight = FontWeight(400)
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    PaymentSection(payment = "$2200.00")
}