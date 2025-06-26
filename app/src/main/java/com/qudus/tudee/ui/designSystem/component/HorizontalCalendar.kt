import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.composable.DayPicker
import com.qudus.tudee.ui.composable.MonthNavigationHeader
import com.qudus.tudee.ui.designSystem.theme.Theme
import java.time.LocalDate


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
            .padding(bottom = 8.dp)
    ) {
        MonthNavigationHeader(
            currentMonth = currentMonth,
            onMonthChange = onMonthChange
        )

        DayPicker(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            onDateSelected = onDateSelected
        )
    }
}


//fun HorizontalCalendar(){
//    var currentMonth by remember { mutableStateOf(LocalDate.now()) }
//    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Theme.color.surfaceHigh)
//            .padding(bottom = 8.dp)
//
//    ) {
//        MonthNavigationHeader(currentMonth) {
//            currentMonth = it
//        }
//        DayPicker(currentMonth, selectedDate) {
//            selectedDate = it
//        }
//    }
//}





