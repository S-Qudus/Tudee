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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.qudus.tudee.designSystem.theme.Theme

@Composable
fun TudeeTextField(
    modifier: Modifier = Modifier,
    icon: Painter,
    hintText: String,
    value: String,
    onTextChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Default,
    onImeOptions: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor by animateColorAsState(
        targetValue = if (isFocused) Theme.color.primary else Theme.color.stroke,
        label = "border color"
    )

    val iconTint by animateColorAsState(
        targetValue = if (isFocused || value.isNotEmpty()) Theme.color.body else Theme.color.hint,
        label = "icon tint"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, color = borderColor, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = "user icon",
                tint = iconTint,
                modifier = Modifier.padding(end = 12.dp)
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(30.dp)
                    .background(Theme.color.stroke)
            )
            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= 100) {
                        onTextChange(newValue)
                    }
                },
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f),
                textStyle = Theme.textStyle.body.medium.copy(color = Theme.color.body),
                interactionSource = interactionSource,
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = hintText,
                            style = Theme.textStyle.label.medium,
                            color = Theme.color.hint
                        )
                    }
                    innerTextField()
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onAny = { onImeOptions() }
                ),
            )
        }
    }
}
