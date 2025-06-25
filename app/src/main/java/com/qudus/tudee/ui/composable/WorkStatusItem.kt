package com.qudus.tudee.ui.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.color.lightThemeColor
import com.qudus.tudee.designSystem.textStyle.Nunito
import com.qudus.tudee.designSystem.textStyle.defaultTextStyle

@Composable
fun WorkStatusItem(workStatus: WorkStatus) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(lightThemeColor.surfaceHigh)
                .padding(start = 25.dp, top = 20.dp, end = 20.dp, bottom = 21.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = workStatus.title,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    fontFamily = Nunito,
                    color = lightThemeColor.title,
                    textAlign = TextAlign.Start,
                    style = defaultTextStyle.title.small
                )

                Image(
                    painter = painterResource(id = workStatus.imageFace),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 8.dp),
                    alignment = Alignment.CenterStart
                )
            }
            SpacerVertical8()

            Text(
                text = workStatus.subtitle,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                fontFamily = Nunito,
                color = lightThemeColor.body,
                textAlign = TextAlign.Start,
                style = defaultTextStyle.body.small
            )
        }
        Image(
            painter = painterResource(id = workStatus.imageTudee),
            contentDescription = null,
            modifier = Modifier
                .width(76.dp)
                .height(92.dp)
                .align(Alignment.CenterEnd)
        )
    }
}

data class WorkStatus(
    val title: String,
    @DrawableRes val imageFace: Int,
    val subtitle: String,
    @DrawableRes val imageTudee: Int,
)

@Preview
@Composable
fun WorkStatusItemPreview() {
    Row {
        WorkStatusItem(
            workStatus = WorkStatus(
                title = "Zero progress?!",
                imageFace = R.drawable.image_angry_face,
                subtitle = "You just scrolling, not working.\nTudee is watching. back to work!!!",
                imageTudee = R.drawable.image_upset_tudee,
            )
        )
    }
}