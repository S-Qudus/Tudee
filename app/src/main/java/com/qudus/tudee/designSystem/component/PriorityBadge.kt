package com.qudus.tudee.designSystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.data.model.Priority
import com.qudus.tudee.ui.util.getColor

@Composable
fun PriorityBadge(priority: Priority, modifier: Modifier = Modifier) {
    Surface(
        color = priority.getColor(),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(priority.iconRes),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(priority.labelResId),
                color = Color.White,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true, name = "High Priority Badge - Light")
@Composable
fun PreviewHighPriorityBadgeLight() {
    MaterialTheme {
        PriorityBadge(priority = Priority.HIGH, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "High Priority Badge - Dark")
@Composable
fun PreviewHighPriorityBadgeDark() {
    MaterialTheme {
        PriorityBadge(priority = Priority.HIGH, modifier = Modifier.padding(8.dp))
    }
}
