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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardAction: ImeAction = ImeAction.Default,
    onKeyboardAction: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val borderColor by animateColorAsState(
        targetValue = if (isFocused) Theme.color.primary else Theme.color.stroke,
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
            interactionSource = interactionSource,
            imeAction = keyboardAction,
            onImeAction = onKeyboardAction,
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
            interactionSource = interactionSource,
            imeAction = keyboardAction,
            onImeAction = onKeyboardAction,
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
    interactionSource: MutableInteractionSource,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
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
            onTextChange = onTextChange,
            interactionSource = interactionSource,
            imeAction = imeAction,
            onImeAction = onImeAction,
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
    onImeAction: () -> Unit,
    hintText: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    maxLength: Int = 100,
) {
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
        keyboardActions = KeyboardActions(onAny = { onImeAction() }),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = hintText,
                    style = Theme.textStyle.label.medium,
                    color = Theme.color.hint
                )
            }
            innerTextField()
        }
    )
}
