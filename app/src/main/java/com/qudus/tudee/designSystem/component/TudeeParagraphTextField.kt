package com.qudus.tudee.designSystem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.qudus.tudee.designSystem.theme.Theme

@Composable
fun TudeeParagraphTextField(
    modifier: Modifier = Modifier,
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

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, color = borderColor, shape = RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= 500) {
                    onTextChange(newValue)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(168.dp),
            singleLine = false,
            textStyle = Theme.textStyle.body.medium.copy(color = Theme.color.body),
            interactionSource = interactionSource,
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