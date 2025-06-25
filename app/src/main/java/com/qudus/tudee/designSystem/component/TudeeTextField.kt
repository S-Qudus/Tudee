package com.qudus.tudee.designSystem.component
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.Theme

@Composable
fun TudeeTextField(
    modifier: Modifier = Modifier,
    value: String,
    tudeeTextFieldType: TudeeTextFieldType,
    onTextChange: (String) -> Unit,
    hintText: String,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: () -> Unit = {},
    maxLines: Int
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

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, color = borderColor, shape = RoundedCornerShape(16.dp))
            .padding(if (tudeeTextFieldType is TudeeTextFieldType.WithIcon) 12.dp else 0.dp)

    ) {
        when (tudeeTextFieldType) {
            is TudeeTextFieldType.Paragraph -> TextField(
                value = value,
                hintText = hintText,
                maxLength = tudeeTextFieldType.maxLength,
                onTextChange = onTextChange,
                interactionSource = interactionSource,
                imeAction = imeAction,
                onImeAction = onImeAction,
                maxLines = maxLines,

                )
            is TudeeTextFieldType.WithIcon -> TextWithIcon(
                value = value,
                hintText = hintText,
                icon = tudeeTextFieldType.icon,
                iconTint = iconTint,
                maxLength = tudeeTextFieldType.maxLength,
                onTextChange = onTextChange,
                interactionSource = interactionSource,
                imeAction = imeAction,
                onImeAction = onImeAction,
                maxLines = maxLines,

                )
        }
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
    modifier: Modifier = Modifier,
    maxLines: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.padding(end = 12.dp)
        )
        Box(
            modifier = Modifier
                .width(maxLines.dp)
                .height(30.dp)
                .background(Theme.color.stroke)
        )
        TextField(
            value = value,
            modifier = Modifier
                .weight(1F)
                .padding(start = 12.dp),
            onTextChange = {
                if (it.length <= maxLength) onTextChange(it)
            },
            interactionSource = interactionSource,
            imeAction = imeAction,
            onImeAction = onImeAction,
            hintText = hintText,
            maxLines = maxLines
        )
    }
}

@Composable
private fun TextField(
    value: String,
    modifier: Modifier = Modifier,
    maxLength: Int = 1,
    onTextChange: (String) -> Unit,
    interactionSource: MutableInteractionSource,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
    hintText: String,
    maxLines: Int
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

sealed class TudeeTextFieldType {
    data class Paragraph(
        val maxLength: Int = 500,
        val maxLines: Int = 10,
        val height: Dp = 168.dp
    ) : TudeeTextFieldType()

    data class WithIcon(
        val icon: Painter,
        val maxLines: Int = 1,
        val maxLength: Int = 100,
        val height: Dp = 56.dp
    ) : TudeeTextFieldType()
}

@Preview(showBackground = true)
@Composable
fun PreviewTudeeTextFieldWithIcon() {
    val text = remember { mutableStateOf("") }

    TudeeTextField(
        value = text.value,
        onTextChange = { text.value = it },
        hintText = "hi there its farah",
        tudeeTextFieldType = TudeeTextFieldType.WithIcon(
            icon = painterResource(id = R.drawable.icon_user)
        ),
        imeAction = ImeAction.Done,
        onImeAction = {},
        maxLines = 1,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTudeeTextFieldParagraph() {
    val text = remember { mutableStateOf("") }

    TudeeTextField(
        value = text.value,
        onTextChange = { text.value = it },
        hintText = "As a squad, our task is to develop a personal task management app for Android. The app should store data locally using the Room database and implement Jetpack Navigation 2 for seamless user flow management.\n" +
                "\n" +
                "Use Koin for dependency injection, ensuring that higher-level modules do not depend on lower-level ones. Adhere strictly to the SOLID principles learned in Season 1.\n" +
                "\n" +
                "Maintain a simple architecture—only include components that are necessary and justified, anything without reason will affect your score in negative way!.\n" +
                "\n" +
                "Each screen is expected to have its own ViewModel. The ViewModel should depend on an abstraction called TasksServices, which will provide domain-level models called entities. The implementation of TasksServices should utilize DAOs from Room to retrieve and map data into domain entities.\n"
        ,tudeeTextFieldType = TudeeTextFieldType.Paragraph(),
        imeAction = ImeAction.Default,
        onImeAction = {},
        maxLines = 10
    )
}