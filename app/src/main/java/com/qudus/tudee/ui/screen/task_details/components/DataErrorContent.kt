package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.exception.ExceptionHandler

@Composable
fun BoxScope.DataErrorContent(modifier: Modifier = Modifier, exception: TudeeExecption) {
    val handler = ExceptionHandler()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .align(alignment = Alignment.Center)
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
            contentDescription = handler.mapExceptionToMessage(exception)
        )

        Text(
            modifier = Modifier.wrapContentHeight(),
            text = handler.mapExceptionToMessage(exception),
            style = Theme.textStyle.body.medium,
            color = Theme.color.pinkAccent
        )
    }
}