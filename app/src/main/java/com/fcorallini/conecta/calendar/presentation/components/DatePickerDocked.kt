package com.fcorallini.conecta.calendar.presentation.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    onDateChange : (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            val selectedDate = Instant.ofEpochMilli(utcTimeMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            return !selectedDate.isBefore(LocalDate.now())
        }
    })

    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let { millis ->
            val localDate = Instant.ofEpochMilli(millis)
                .atZone(ZoneId.of("UTC"))
                .toLocalDate()
            onDateChange(localDate)
        }
    }
    DatePicker(
        state = datePickerState,
        showModeToggle = false
    )
}

