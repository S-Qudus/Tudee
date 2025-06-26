package com.qudus.tudee.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.TudeeHeader.TudeeHeader
import com.qudus.tudee.ui.designSystem.component.ThemeSwitchButton.ThemeSwitchButton
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.tooling.preview.Preview

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    var isDarkMode by remember { mutableStateOf(false) }
    val today = remember {
        LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy"))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Top App Bar
        TudeeHeader(
            endContent = {
                ThemeSwitchButton(
                    isDarkMode = isDarkMode,
                    onCheckedChange = { isDarkMode = it },
                    modifier = Modifier.padding(8.dp)
                )
            }
        )
    }

//        Spacer(modifier = Modifier.height(12.dp))
//
//        // Status Section
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(modifier = Modifier.weight(1f)) {
//                    Text(
//                        text = today,
//                        color = Color.Gray
//                    )
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Text(
//                        text = stringResource(R.string.stay_working),
//                    )
//                    Spacer(modifier = Modifier.height(2.dp))
//                    Text(
//                        text = stringResource(R.string.tasks_completed_message, 3, 10),
//                        color = Color.Gray
//                    )
//                }
//                Spacer(modifier = Modifier.width(8.dp))
//                Image(
//                    painter = painterResource(R.drawable.image_happy_tudee),
//                    contentDescription = null,
//                    modifier = Modifier.size(64.dp)
//                )
//            }
//        }
//
//        // TODO: Add Overview Section here
//        // TODO: Add In Progress Section here
//        // TODO: Add FAB and BottomNavBar
//    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}