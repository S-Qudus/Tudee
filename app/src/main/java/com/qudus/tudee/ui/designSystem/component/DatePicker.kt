package com.qudus.tudee.designSystem.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.time.LocalDate
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.qudus.tudee.ui.designSystem.theme.Theme
import java.time.ZoneId


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DatePicker(
      showDialog: Boolean,
      selectedDate: LocalDate?,
      onDismiss: () -> Unit,
      onClear: () -> Unit,
      onDateSelected: (LocalDate) -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate
            ?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
            ?: LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    )

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { onDismiss },
            confirmButton = {},
            dismissButton = {},
            properties = DialogProperties()
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                DatePicker(state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        selectedDayContainerColor  =  Theme.color.primary,
                        selectedYearContainerColor  =  Theme.color.primary,
                        todayDateBorderColor =  Theme.color.primary,
                    ),)

                Row(
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                    text = "Clear",
                        modifier = Modifier.clickable {
                            onClear()
                            onDismiss()
                    },
                        style =  Theme.textStyle.label.large,
                        color = Theme.color.primary)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                Row() {
                    Text(text = "Cancel",
                        style =  Theme.textStyle.label.large,
                        color = Theme.color.primary,
                        modifier = Modifier.clickable { onDismiss() })


                Text(
                    text = "OK",
                    modifier = Modifier.clickable {
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            val date = LocalDate.ofEpochDay(millis / (24 * 60 * 60 * 1000))
                            onDateSelected(date)
                        }
                        onDismiss()
                    },
                    style = Theme.textStyle.label.large,
                    color =Theme.color.primary
                )
                }
                }
            }
        }

}




