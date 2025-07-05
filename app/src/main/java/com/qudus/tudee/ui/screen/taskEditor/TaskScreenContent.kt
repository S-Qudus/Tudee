package com.qudus.tudee.ui.screen.taskEditor

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.DatePicker
import com.qudus.tudee.ui.designSystem.component.CategoryBadgeItem
import com.qudus.tudee.ui.designSystem.component.TitledSection
import com.qudus.tudee.ui.designSystem.component.buttons.TudeeButton
import com.qudus.tudee.ui.designSystem.component.TudeeCheckBadge
import com.qudus.tudee.ui.designSystem.component.TudeeBottomSheet
import com.qudus.tudee.ui.designSystem.component.text_field.TudeeTextField
import com.qudus.tudee.ui.designSystem.component.text_field.TudeeTextFieldType.Paragraph
import com.qudus.tudee.ui.designSystem.component.text_field.TudeeTextFieldType.WithIcon
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.addTask.state.AddTaskInteraction
import com.qudus.tudee.ui.screen.taskEditor.composable.PriorityChip
import com.qudus.tudee.ui.screen.taskEditor.composable.getCategoryErrorMessage
import com.qudus.tudee.ui.screen.taskEditor.composable.getTaskDataErrorMessageByType
import com.qudus.tudee.ui.screen.taskEditor.composable.getTitleErrorMessage
import com.qudus.tudee.ui.util.getDefaultCategoryStringResourceByType
import com.qudus.tudee.ui.util.getIconPainterForCategory
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDate
import java.io.File

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskScreenContent(
    modifier: Modifier = Modifier,
    state: TaskEditorUiState,
    interaction: TaskEditorInteraction,
    onPrimaryActionClick: () -> Unit,
) {

    TudeeBottomSheet(
        modifier = modifier,
        isSheetOpen = true,
        onDismissRequest = interaction::onCancelChangeTask
    ) {
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(.8f)) {
            if (state.dataErrorMessageType == null) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 148.dp)
                ) {
                    item { TaskInfoSection(state = state, interaction = interaction) }
                    item { PrioritySection(state = state, interaction = interaction) }
                    item { CategorySection(state = state, interaction = interaction) }
                }

                PrimaryActionsSection(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    state = state,
                    interaction = interaction,
                    onPrimaryActionClick = { onPrimaryActionClick() }
                )
            } else {
                DataErrorContent(
                    dataErrorType = state.dataErrorMessageType,
                    modifier = Modifier.padding(horizontal = Theme.dimension.spacing16)
                )
            }
        }
    }

    DatePicker(
        showDialog = state.isDatePickerOpen,
        selectedDate = state.date.toLocalDate(),
        onDismiss = interaction::onDatePickCancel,
        onClear = {
            state.date.toLocalDate()
                .atStartOfDayIn(TimeZone.currentSystemDefault())
                .toEpochMilliseconds()
        },
        onDateSelected = interaction::onDateSelected
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun TaskInfoSection(state: TaskEditorUiState, interaction: TaskEditorInteraction) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    TitledSection(
        modifier = Modifier.padding(horizontal = Theme.dimension.spacing16),
        title = if (interaction is AddTaskInteraction) stringResource(R.string.add_new_task)
        else stringResource(R.string.edit_task),
        titleStyle = Theme.textStyle.title.large
    ) {
        TudeeTextField(
            modifier = Modifier
                .padding(top = Theme.dimension.spacing12)
                .focusRequester(focusRequester),
            value = state.title,
            type = WithIcon(icon = painterResource(R.drawable.icon_note_stroke)),
            onValueChange = interaction::onTitleValueChange,
            placeholder = stringResource(R.string.task_title),
            primaryBordColor = if (state.titleErrorMessageType != null) Theme.color.pinkAccent else Theme.color.primary,
            imeAction = ImeAction.Next,
            keyboardAction = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )
        AnimatedVisibility(
            visible = state.titleErrorMessageType != null,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Text(
                modifier = Modifier.padding(top = Theme.dimension.spacing2),
                text = getTitleErrorMessage(state.titleErrorMessageType ?: TitleErrorType.INVALID),
                style = Theme.textStyle.label.small,
                color = Theme.color.pinkAccent
            )
        }

        TudeeTextField(
            modifier = Modifier.padding(top = Theme.dimension.spacing16),
            value = state.description,
            type = Paragraph(),
            onValueChange = interaction::onDescriptionValueChange,
            placeholder = stringResource(R.string.description),
            imeAction = ImeAction.Done,
            keyboardAction = KeyboardActions(onDone = null)

        )

        TudeeTextField(
            modifier = Modifier
                .padding(top = Theme.dimension.spacing16)
                .clickable(onClick = interaction::onDateFieldClick),
            value = state.date,
            type = WithIcon(icon = painterResource(R.drawable.icon_calendar_add)),
            onValueChange = {},
            readOnly = true,
            enabled = false,
            placeholder = "",
        )
    }
}

@Composable
private fun PrioritySection(
    state: TaskEditorUiState,
    interaction: TaskEditorInteraction,
    modifier: Modifier = Modifier
) {
    TitledSection(
        modifier = modifier.padding(Theme.dimension.spacing16),
        title = stringResource(R.string.priority),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Theme.dimension.spacing8),
            horizontalArrangement = Arrangement.spacedBy(Theme.dimension.spacing8)
        ) {
            state.priorityUiStates.forEach { priorityState ->
                key(priorityState.type) {
                    PriorityChip(
                        isActive = priorityState.isSelected,
                        onChipClick = interaction::onPrioritySelectChange,
                        priorityType = priorityState.type,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CategorySection(
    state: TaskEditorUiState,
    interaction: TaskEditorInteraction,
    modifier: Modifier = Modifier
) {
    TitledSection(
        modifier = modifier.padding(horizontal = Theme.dimension.spacing16),
        title = stringResource(R.string.category),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(104.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 300.dp, max = 1000.dp)
                .padding(top = Theme.dimension.spacing8),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(Theme.dimension.spacing24),
        ) {
            items(state.categoryUiStates, key = { it.id }) { category ->
                CategoryBadgeItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    id = category.id,
                    title = if (category.defaultCategoryType == null) category.title
                    else getDefaultCategoryStringResourceByType(category.defaultCategoryType),
                    isClickable = true,
                    onItemClick = interaction::onCategoryTypeSelectChange,
                    imagePainter = if (category.defaultCategoryType != null) {
                        getIconPainterForCategory(category.defaultCategoryType)
                    } else {
                        rememberAsyncImagePainter(model = File(category.imagePath))
                    },
                ) {
                    TudeeCheckBadge(
                        modifier = Modifier.align(Alignment.TopEnd),
                        visible = category.isSelected
                    )
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            visible = state.categoryErrorMessageType != null,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Text(
                modifier = Modifier.padding(top = Theme.dimension.spacing24),
                text = getCategoryErrorMessage(
                    state.categoryErrorMessageType ?: CategoryErrorType.NOT_FOUND
                ),
                style = Theme.textStyle.body.medium,
                color = Theme.color.pinkAccent
            )
        }
    }
}

@Composable
private fun PrimaryActionsSection(
    state: TaskEditorUiState,
    interaction: TaskEditorInteraction,
    onPrimaryActionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Theme.color.surface)
            .padding(horizontal = Theme.dimension.spacing16, vertical = Theme.dimension.spacing12),
        verticalArrangement = Arrangement.spacedBy(Theme.dimension.spacing12)
    ) {
        TudeeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onPrimaryActionClick() },
            isLoading = state.isLoading,
            isEnabled = state.isPrimaryButtonEnabled,
        ) {
            Text(
                text = if (interaction is AddTaskInteraction) stringResource(R.string.add) else stringResource(
                    R.string.edit
                ),
                style = Theme.textStyle.label.large,
                color = Theme.color.onPrimary
            )
        }

        TudeeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = interaction::onCancelChangeTask,
            isLoading = false,
            isEnabled = true,
            hasBorder = true,
        ) {
            Text(
                text = stringResource(R.string.cancel),
                style = Theme.textStyle.label.large,
                color = Theme.color.primary
            )
        }
    }
}

@Composable
private fun BoxScope.DataErrorContent(modifier: Modifier = Modifier, dataErrorType: DataErrorType) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .align(Alignment.Center)
            .border(width = 2.dp, color = Theme.color.pinkAccent, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(color = Theme.color.pinkAccent.copy(alpha = .2f))
            .padding(Theme.dimension.spacing16)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(Theme.dimension.spacing16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(R.drawable.icon_alert),
            tint = Theme.color.pinkAccent,
            contentDescription = getTaskDataErrorMessageByType(dataErrorType)
        )

        Text(
            modifier = Modifier.wrapContentHeight(),
            text = getTaskDataErrorMessageByType(dataErrorType),
            style = Theme.textStyle.body.medium,
            color = Theme.color.pinkAccent
        )
    }
}