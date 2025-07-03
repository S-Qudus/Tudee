package com.qudus.tudee.ui.screen.addTask

//import DatePicker
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.qudus.tudee.ui.designSystem.component.TudeeBottomSheet
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.addTask.state.AddTaskUiState
import com.qudus.tudee.ui.screen.addTask.state.AddTaskInteraction
import com.qudus.tudee.ui.screen.taskEditor.TitleErrorType
import com.qudus.tudee.ui.screen.taskEditor.CategoryErrorType
import com.qudus.tudee.ui.util.getDefaultCategoryStringResourceByType
import com.qudus.tudee.ui.util.getIconPainterForCategory
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import coil.compose.rememberAsyncImagePainter
import com.qudus.tudee.designSystem.component.DatePicker
import com.qudus.tudee.ui.designSystem.component.CategoryBadgeItem
import com.qudus.tudee.ui.designSystem.component.TitledSection
import com.qudus.tudee.ui.designSystem.component.buttons.TudeeButton
import com.qudus.tudee.ui.designSystem.component.TudeeCheckBadge
import java.io.File
import com.qudus.tudee.ui.designSystem.component.text_field.TudeeTextField
import com.qudus.tudee.ui.designSystem.component.text_field.TudeeTextFieldType.Paragraph
import com.qudus.tudee.ui.designSystem.component.text_field.TudeeTextFieldType.WithIcon
import com.qudus.tudee.ui.screen.taskEditor.composable.PriorityChip
import kotlinx.datetime.toLocalDate
import com.qudus.tudee.ui.screen.addTask.util.getCategoryErrorMessage
import com.qudus.tudee.ui.screen.addTask.util.getTitleErrorMessage

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onTaskAdded: () -> Unit = {},
    viewModel: AddTaskViewModel = koinViewModel { parametersOf(onDismiss, onTaskAdded) }
) {
    val state by viewModel.state.collectAsState()

    AddTaskScreenContent(
        modifier = modifier,
        state = state,
        interaction = viewModel,
        onDismiss = onDismiss
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun AddTaskScreenContent(
    modifier: Modifier = Modifier,
    state: AddTaskUiState,
    interaction: AddTaskInteraction,
    onDismiss: () -> Unit = {}
) {

    TudeeBottomSheet(
        modifier = modifier,
        isSheetOpen = true, 
        onDismissRequest = onDismiss
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
        modifier = Modifier.padding(horizontal = Theme.dimension.spacing16),
        title = stringResource(R.string.add_new_task),
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
    state: AddTaskUiState,
    interaction: AddTaskInteraction,
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
    state: AddTaskUiState,
    interaction: AddTaskInteraction,
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
    state: AddTaskUiState,
    interaction: AddTaskInteraction,
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
            onClick = interaction::onAddTaskClicked,
            isLoading = state.isLoading,
            isEnabled = state.isPrimaryButtonEnabled,
        ) {
            Text(
                text = stringResource(R.string.add),
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
        ) {
            Text(
                text = stringResource(R.string.cancel),
                style = Theme.textStyle.label.large,
                color = Theme.color.primary
            )
        }
    }
}