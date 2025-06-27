package com.qudus.tudee.designSystem.component.text_field

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun TudeeTextField(
    modifier: Modifier = Modifier,
    value: String,
    type: TudeeTextFieldType,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit,
    placeholder: String,
    imeAction: ImeAction = ImeAction.Default,
    keyboardAction: KeyboardActions = KeyboardActions(),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val borderColor by animateColorAsState(
        targetValue = if (isFocused && !readOnly) Theme.color.primary else Theme.color.stroke,
        label = "border color"
    )
    val iconTint by animateColorAsState(
        targetValue = if ((isFocused || value.isNotEmpty())) Theme.color.body else Theme.color.hint,
        label = "icon tint"
    )
    when (type) {
        is TudeeTextFieldType.Paragraph -> TextField(
            value = value,
            hintText = placeholder,
            maxLength = type.maxLength,
            onTextChange = onValueChange,
            readOnly = readOnly,
            interactionSource = interactionSource,
            imeAction = imeAction,
            keyboardAction = keyboardAction,
            maxLines = type.maxLines,
            modifier = modifier
                .border(1.dp, color = borderColor, shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp)
                .height(type.height)
        )

        is TudeeTextFieldType.WithIcon -> TextWithIcon(
            value = value,
            hintText = placeholder,
            icon = type.icon,
            iconTint = iconTint,
            maxLength = type.maxLength,
            onTextChange = onValueChange,
            readOnly = readOnly,
            interactionSource = interactionSource,
            imeAction = imeAction,
            keyboardAction = keyboardAction,
            maxLines = type.maxLines,
            modifier = modifier
                .border(1.dp, color = borderColor, shape = RoundedCornerShape(16.dp))
                .height(type.height)
        )
    }
}

@Composable
private fun TextWithIcon(
    value: String,
    hintText: String,
    icon: Painter,
    iconTint: Color,
    maxLength: Int,
    onTextChange: (String) -> Unit,
    readOnly: Boolean = false,
    interactionSource: MutableInteractionSource,
    imeAction: ImeAction,
    keyboardAction: KeyboardActions = KeyboardActions(),
    maxLines: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier
        )
        Box(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
                .width(1.dp)
                .height(30.dp)
                .background(Theme.color.stroke)
        )
        TextField(
            value = value,
            modifier = Modifier
                .weight(1F),
            readOnly = readOnly,
            onTextChange = onTextChange,
            interactionSource = interactionSource,
            imeAction = imeAction,
            keyboardAction = keyboardAction,
            hintText = hintText,
            maxLines = maxLines,
            maxLength = maxLength
        )
    }
}

@Composable
private fun TextField(
    value: String,
    onTextChange: (String) -> Unit,
    interactionSource: MutableInteractionSource,
    imeAction: ImeAction,
    keyboardAction: KeyboardActions = KeyboardActions(),
    hintText: String,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    maxLength: Int = 100,
) {
    val defaultVerticalPadding = 12.dp
    var innerVerticalPadding by remember { mutableStateOf(defaultVerticalPadding) }
    var lineCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(lineCount) {
        innerVerticalPadding = if (lineCount >= maxLines) 0.dp else defaultVerticalPadding

    }

    BasicTextField(
        value = value,
        onValueChange = {
            onTextChange(it.take(maxLength))
        },
        modifier = modifier
            .fillMaxWidth(),
        maxLines = maxLines,
        textStyle = Theme.textStyle.body.medium.copy(color = Theme.color.body),
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = keyboardAction,
        readOnly = readOnly,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = innerVerticalPadding)
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hintText,
                        style = Theme.textStyle.label.medium,
                        color = Theme.color.hint
                    )
                }
                innerTextField()
            }
        }
        ,
        onTextLayout = {lineCount = it.lineCount }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTudeeTextFieldWithIcon() {
    val text = remember { mutableStateOf("") }

    TudeeTextField(
        value = text.value,
        onValueChange = { text.value = it },
        placeholder = "hi there its farah",
        type = TudeeTextFieldType.WithIcon(
            icon = painterResource(id = R.drawable.icon_user)
        ),
        imeAction = ImeAction.Done,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTudeeTextFieldParagraph() {
    val text = remember { mutableStateOf("") }

    TudeeTextField(
        value = text.value,
        onValueChange = { text.value = it },
        placeholder = "As a squad, our task is to develop a personal task management app for Android. The app should store data locally using the Room database and implement Jetpack Navigation 2 for seamless user flow management.\n" +
                "\n" +
                "Use Koin for dependency injection, ensuring that higher-level modules do not depend on lower-level ones. Adhere strictly to the SOLID principles learned in Season 1.\n" +
                "\n" +
                "Maintain a simple architecture—only include components that are necessary and justified, anything without reason will affect your score in negative way!.\n" +
                "\n" +
                "Each screen is expected to have its own ViewModel. The ViewModel should depend on an abstraction called TasksServices, which will provide domain-level models called entities. The implementation of TasksServices should utilize DAOs from Room to retrieve and map data into domain entities.\n",
        type = TudeeTextFieldType.Paragraph(),
        imeAction = ImeAction.Default,
    )
}