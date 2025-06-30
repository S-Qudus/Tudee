import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import kotlinx.datetime.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    showDialog: Boolean,
    selectedDate: LocalDate?,
    onDismiss: () -> Unit,
    onClear: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    val timeZone = TimeZone.currentSystemDefault()
    val today = Clock.System.now().toLocalDateTime(timeZone).date

    val initialMillis = (selectedDate ?: today)
        .atStartOfDayIn(timeZone)
        .toEpochMilliseconds()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialMillis
    )

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {},
            dismissButton = {},
            properties = DialogProperties()
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
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
                            .padding(end = 32.dp)
                            .clickable { onDismiss() },
                    )

                    Text(
                        text = stringResource(R.string.ok),
                        modifier = Modifier.clickable {
                            val millis = datePickerState.selectedDateMillis
                            if (millis != null) {
                                val selected = Instant.fromEpochMilliseconds(millis)
                                    .toLocalDateTime(timeZone)
                                    .date
                                onDateSelected(selected)
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
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DatePickerPreview() {
    DatePicker(
        showDialog = true,
        selectedDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        onDismiss = {},
        onClear = {},
        onDateSelected = {}
    )
}