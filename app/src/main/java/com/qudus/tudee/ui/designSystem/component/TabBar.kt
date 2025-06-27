package com.qudus.tudee.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.designSystem.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBar(
    selectedState: State,
    countForState: Map<State, Int>,
    onStateSelected: (State) -> Unit,
    modifier: Modifier,
) {
    val tabs = State.values()
    val selectedTabIndex = tabs.indexOf(selectedState)


    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Theme.color.surfaceHigh,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
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
        tabs.forEachIndexed { index, state ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onStateSelected(state)
                },
                selectedContentColor = Theme.color.title,
                unselectedContentColor = Theme.color.hint,
                interactionSource = remember { MutableInteractionSource() },
                modifier = Modifier.indication(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ),
                text = {
                    TaskTabItem(
                        tab = state,
                        isSelected = selectedTabIndex == index,
                        count = countForState[state] ?: 0
                    )
                }
            )
        }
    }
}


@Preview
@Composable
private fun TapBarPreview() {
    TabBar(
        selectedState = State.TODO, onStateSelected = {}, modifier = Modifier,
        countForState = TODO(),
    )
}