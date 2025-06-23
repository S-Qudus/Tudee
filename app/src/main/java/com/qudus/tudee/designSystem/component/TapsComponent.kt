package com.qudus.tudee.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.designSystem.theme.Theme

@Composable
fun TapsComponent() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(
        TabItem("In progress", 14),
        TabItem("To Do", 0),
        TabItem("Done", 0)
    )

    Column(
        modifier = Modifier
            .height(48.dp)
            .background(Theme.color.surfaceHigh),
        verticalArrangement = Arrangement.Center,
    ) {
        TabRow(
            modifier = Modifier
                .padding(start = 16.dp)
                .background(Color.Transparent),
            containerColor = Theme.color.surfaceHigh,
            selectedTabIndex = selectedTabIndex,
            divider = {},
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .width(tabPositions[selectedTabIndex].width)
                        .height(4.dp)
                        .background(
                            Theme.color.secondary,
                            RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                        )
                        .align(Alignment.CenterHorizontally)
                )
            },
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selectedContentColor = Theme.color.title,
                    unselectedContentColor = Theme.color.hint,
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(bottom = 8.dp),
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = tab.title,
                            style = if (selectedTabIndex == index)
                                Theme.textStyle.label.medium else Theme.textStyle.label.small
                        )
                        if (tab.count > 0) {
                            Spacer(Modifier.width(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(Theme.color.surface, CircleShape),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = tab.count.toString(),
                                    textAlign = TextAlign.Center,
                                    color = Theme.color.body,
                                    style = if (selectedTabIndex == index)
                                        Theme.textStyle.label.medium else Theme.textStyle.label.small
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

data class TabItem(val title: String, val count: Int)

@Preview
@Composable
fun TaskScreen() {
    TapsComponent()
}