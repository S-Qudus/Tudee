package com.qudus.tudee.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qudus.tudee.designSystem.color.LocalTudeeColors
import com.qudus.tudee.designSystem.color.TudeeColors
import com.qudus.tudee.designSystem.model.BottomNavItem

@Composable
fun BottomNavBar(
    navController : NavController,
    items : List<BottomNavItem>,
    selectedRoute: String,
    ) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .shadow(
            elevation = 12.dp,
            ambientColor = Color(0x14000000),
            spotColor = Color(0x14000000)
        )
        .background(TudeeTheme.color.surfaceHigh),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            val isSelected = item.route == selectedRoute
            Box(modifier = Modifier.weight(1f)
                .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }
                ){
                if (item.route != selectedRoute){
                    navController.navigate(item.route)
                }
            },
                contentAlignment = Alignment.Center){
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(42.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(if (isSelected) TudeeTheme.color.primaryVariant else Color.Transparent)
                        ,
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = if (isSelected) item.iconFill else item.iconStroke,
                        contentDescription = "Bottom Nav Bar Icon",
                        modifier = Modifier.size(24.dp),
                        tint =if (isSelected) TudeeTheme.color.primary else TudeeTheme.color.hint,
                    )
                }
            }
        }

    }

}
object TudeeTheme {
    val color: TudeeColors
        @Composable
        get() = LocalTudeeColors.current
}

