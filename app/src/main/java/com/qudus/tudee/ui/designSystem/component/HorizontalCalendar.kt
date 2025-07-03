import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qudus.tudee.ui.designSystem.component.DayPicker
import com.qudus.tudee.ui.designSystem.component.MonthNavigationHeader
import com.qudus.tudee.ui.designSystem.theme.Theme
import kotlinx.datetime.LocalDate



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorizontalCalendar(
    currentMonth: LocalDate,
    onMonthChange: (LocalDate) -> Unit,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.color.surfaceHigh)
            .padding(bottom = Theme.dimension.spacing8)
    ) {
        MonthNavigationHeader(
            currentMonth = currentMonth,
            onMonthChange = onMonthChange,
            selectedDate  = selectedDate,
            onDatePicked  = { picked ->
                val firstDay = LocalDate(picked.year, picked.month, 1)
                if (firstDay != currentMonth) onMonthChange(firstDay)
                onDateSelected(picked)
            }
        )

        DayPicker(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            onDateSelected = onDateSelected
        )
    }
}





