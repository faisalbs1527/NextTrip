package com.example.nexttrip.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.ui.theme.NextTripTheme
import com.example.nexttrip.ui.theme.red40
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModel(
    initialDate: Long,
    fromDate:Long,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate,
        selectableDates =
        object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= fromDate
            }
        }
    )

    DatePickerDialog(
        modifier = Modifier.padding(12.dp),
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text(
                    text = "OK",
                    fontSize = 16.sp,
                    color = red40
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Cancel",
                    fontSize = 16.sp,
                    color = red40
                )
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    NextTripTheme {

        DatePickerModel(
            initialDate = System.currentTimeMillis(),
            fromDate = System.currentTimeMillis(),
            onDateSelected = {

            },
            onDismiss = {

            }
        )
    }
}

fun formatDate(selectedDate: Long): String {
    val date = Date(selectedDate)
    val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
    val formattedDate = dateFormat.format(date)
    return formattedDate
}

fun getNextDate(currentDate: Long): String {
    val date = Date(currentDate)
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.DAY_OF_MONTH, 1)
    val nextDate = calendar.time
    val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
    return dateFormat.format(nextDate)
}

fun formattedDateToMillis(formattedDate: String):Long{
    val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
    val date = dateFormat.parse(formattedDate)
    return date.time
}

