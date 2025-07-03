package com.qudus.tudee.designSystem.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn


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

    val zone = TimeZone.currentSystemDefault()

    val selectedInstant = selectedDate?.atStartOfDayIn(zone)?.toEpochMilliseconds()
        ?: Clock.System.todayIn(zone).atStartOfDayIn(zone).toEpochMilliseconds()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedInstant
    )

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = onDismiss ,
            confirmButton = {},
            dismissButton = {},
            properties = DialogProperties()
        ) {
            Column(modifier = Modifier) {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        selectedDayContainerColor = Theme.color.primary,
                        selectedYearContainerColor = Theme.color.primary,
                        todayDateBorderColor = Theme.color.primary,
                    ),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, end = 18.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.clear),
                        modifier = Modifier.clickable {
                            onClear()
                            onDismiss()
                        },
                        style = Theme.textStyle.label.large,
                        color = Theme.color.primary
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(R.string.cancel),
                        style = Theme.textStyle.label.large,
                        color = Theme.color.primary,
                        modifier = Modifier
                            .clickable { onDismiss() }
                            .padding(end = 16.dp),
                    )

                    Text(
                        text = stringResource(R.string.ok),
                        modifier = Modifier.clickable {
                            val millis = datePickerState.selectedDateMillis
                            if (millis != null) {
                                val date = Instant.fromEpochMilliseconds(millis)
                                    .toLocalDateTime(TimeZone.currentSystemDefault()).date
                                onDateSelected(date)
                            }
                            onDismiss()
                        },
                        style = Theme.textStyle.label.large,
                        color = Theme.color.primary
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
fun DatePickerPreview() {
    DatePicker(
        showDialog = true,
        selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
        onDismiss = {},
        onClear = {},
        onDateSelected = {}
    )
}




