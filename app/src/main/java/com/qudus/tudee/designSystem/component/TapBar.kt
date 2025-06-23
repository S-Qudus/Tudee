package com.qudus.tudee.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.qudus.tudee.designSystem.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TapBar(
    startTab: TaskStatus = TaskStatus.IN_PROGRESS,
    onTabSelected: (TaskStatus) -> Unit = {},
) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(startTab.ordinal) }
    val tabs = TaskStatus.values()

    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Theme.color.surfaceHigh,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(start = 16.dp),
        divider = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Theme.color.stroke)
            )
        },
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier
                    .tabIndicatorOffset(selectedTabIndex, matchContentSize = true),
                height = 4.dp,
                width = Dp.Unspecified,
                color = Theme.color.secondary,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(tab)
                },
                selectedContentColor = Theme.color.title,
                unselectedContentColor = Theme.color.hint,
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = tab.title,
                            textAlign = TextAlign.Center,
                            style = if (selectedTabIndex == index)
                                Theme.textStyle.label.medium else Theme.textStyle.label.small,
                        )

                        if (tab.count > 0 && selectedTabIndex == index) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(Theme.color.surface, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = tab.count.toString(),
                                    style = Theme.textStyle.label.medium,
                                    color = Theme.color.body
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}

enum class TaskStatus(val title: String, val count: Int) {
    IN_PROGRESS("In progress", 14),
    TODO("To Do", 0),
    DONE("Done", 0)
}

@Preview
@Composable
fun TapBarPreview() {
    TapBar()
}