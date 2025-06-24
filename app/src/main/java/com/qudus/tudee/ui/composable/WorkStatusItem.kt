package com.qudus.tudee.ui.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun WorkStatusItem(
    title: String,
    @DrawableRes imageFace: Int,
    subtitle: String,
    @DrawableRes imageTudee: Int
) {
    Box(
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .fillMaxSize()
            .padding(start = 25.dp, top = 20.dp, end = 20.dp, bottom = 21.dp)
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    fontFamily = Nunito,
                    color = lightThemeColor.title,
                    textAlign = TextAlign.Start,
                    style = defaultTextStyle.title.small
                )

                SpacerHorizontal8()

                Image(
                    painter = painterResource(id = imageFace),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp),
                    alignment = Alignment.CenterStart
                )
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = subtitle,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    fontFamily = Nunito,
                    color = lightThemeColor.body,
                    textAlign = TextAlign.Start,
                    style = defaultTextStyle.body.small
                )
            }
        }
    }
    Image(
        painter = painterResource(id = imageTudee),
        contentDescription = "Tudee",
        modifier = Modifier
            .width(76.dp)
            .height(92.dp)
            .padding(end = 25.dp),
        alignment = Alignment.CenterEnd
    )
}

@Preview
@Composable
fun WorkStatusItemPreview() {
    Row {
        WorkStatusItem(
            title = "Title",
            imageFace = R.drawable.happy_face,
            subtitle = "Sub",
            imageTudee = R.drawable.upset_tudee,
        )
    }
}