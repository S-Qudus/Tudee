package com.qudus.tudee.ui.screen.addTask

import DatePicker
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.text_field.TudeeTextField
import com.qudus.tudee.designSystem.component.text_field.TudeeTextFieldType.Paragraph
import com.qudus.tudee.designSystem.component.text_field.TudeeTextFieldType.WithIcon
import com.qudus.tudee.ui.composable.CategoryBadgeItem
import com.qudus.tudee.ui.composable.ImageFromFilePath
import com.qudus.tudee.ui.composable.ImageFromRes
import com.qudus.tudee.ui.composable.TitledSection
import com.qudus.tudee.ui.composable.TudeeButton
import com.qudus.tudee.ui.composable.TudeeCheckBadge
import com.qudus.tudee.ui.designSystem.component.TudeeBottomSheet
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState.CategoryErrorType
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState.TitleErrorType
import com.qudus.tudee.ui.screen.addTask.composable.PriorityChip
import com.qudus.tudee.ui.screen.addTask.composable.getCategoryErrorMessage
import com.qudus.tudee.ui.screen.addTask.composable.getTitleErrorMessage
import com.qudus.tudee.ui.util.getDefaultCategoryStringResourceByType
import com.qudus.tudee.ui.util.getIconResForCategory
import kotlinx.datetime.toLocalDate
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: AddTaskViewModel = koinViewModel<AddTaskViewModel>()
) {
    val state by viewModel.state.collectAsState()

    AddTaskScreenContent(modifier = modifier, interaction = viewModel, state = state)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun AddTaskScreenContent(
    modifier: Modifier = Modifier,
    state: AddTaskUiState,
    interaction: AddTaskInteraction
) {

    TudeeBottomSheet(
        modifier = modifier,
        isSheetOpen = state.isSheetOpen,
        onDismissRequest = interaction::onCancelAddTask
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
        ) {
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
                interaction = interaction
            )
        }
    }

    DatePicker(
        showDialog = state.isDatePickerOpen,
        selectedDate = state.date.toLocalDate(),
        onDismiss = interaction::onDatePickCancel,
        onClear = interaction::onDatePickCancel,
        onDateSelected = interaction::onDateSelected
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun TaskInfoSection(state: AddTaskUiState, interaction: AddTaskInteraction) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    TitledSection(
        modifier = Modifier.padding(horizontal = Theme.dimension.medium),
        title = stringResource(R.string.add_new_task),
        titleStyle = Theme.textStyle.title.large
    ) {
        TudeeTextField(
            modifier = Modifier
                .padding(top = Theme.dimension.smallMedium)
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
                modifier = Modifier.padding(top = Theme.dimension.extraSmall),
                text = getTitleErrorMessage(state.titleErrorMessageType ?: TitleErrorType.INVALID),
                style = Theme.textStyle.label.small,
                color = Theme.color.pinkAccent
            )
        }

        TudeeTextField(
            modifier = Modifier.padding(top = Theme.dimension.medium),
            value = state.description,
            type = Paragraph(),
            onValueChange = interaction::onDescriptionValueChange,
            placeholder = stringResource(R.string.description),
            imeAction = ImeAction.Done,
            keyboardAction = KeyboardActions(onDone = null)

        )

        TudeeTextField(
            modifier = Modifier
                .padding(top = Theme.dimension.medium)
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
    state: AddTaskUiState,
    interaction: AddTaskInteraction,
    modifier: Modifier = Modifier
) {
    TitledSection(
        modifier = modifier.padding(Theme.dimension.medium),
        title = stringResource(R.string.priority),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Theme.dimension.small),
            horizontalArrangement = Arrangement.spacedBy(Theme.dimension.small)
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
    state: AddTaskUiState,
    interaction: AddTaskInteraction,
    modifier: Modifier = Modifier
) {
    TitledSection(
        modifier = modifier.padding(horizontal = Theme.dimension.medium),
        title = stringResource(R.string.category),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(104.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 300.dp, max = 1000.dp) //((state.categoryUiStates.count() * 102) - (state.categoryUiStates.count() * 24)).dp
                .padding(top = Theme.dimension.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(Theme.dimension.largeMedium),
            //userScrollEnabled = false
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
                    contentImage = if (category.defaultCategoryType != null) {
                        {
                            ImageFromRes(
                                drawableResId = getIconResForCategory(category.defaultCategoryType),
                                contentDescription = category.title
                            )
                        }
                    } else {
                        {
                            ImageFromFilePath(
                                imagePath = category.imagePath,
                                contentDescription = category.title
                            )
                        }
                    }
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
                modifier = Modifier.padding(top = Theme.dimension.largeMedium),
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
    state: AddTaskUiState,
    interaction: AddTaskInteraction,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Theme.color.surface)
            .padding(horizontal = Theme.dimension.medium, vertical = Theme.dimension.smallMedium),
        verticalArrangement = Arrangement.spacedBy(Theme.dimension.smallMedium)
    ) {
        TudeeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = interaction::onAddTaskClicked,
            isLoading = state.isLoading,
            isEnabled = state.isAddButtonEnabled,
        ){
            Text(
                text =  stringResource(R.string.add),
                style = Theme.textStyle.label.large,
                color = Theme.color.onPrimary
            )
        }

        TudeeButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = interaction::onCancelAddTask,
            isLoading = false,
            isEnabled = true,
            hasBorder = true,
        ){
            Text(
                text = stringResource(R.string.cancel),
                style = Theme.textStyle.label.large,
                color = Theme.color.primary
            )
        }
    }
}