package com.qudus.tudee.ui.composable.confirm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.R
import com.qudus.tudee.ui.composable.TudeeTextButton
import com.qudus.tudee.ui.designSystem.component.TudeeBottomSheet


@Composable
fun ConfirmDelete(
    modifier: Modifier = Modifier,
    title : String,
    description : String,
    image : Painter,
    onCancel : () -> Unit,
    onConfirmDelete: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(Theme.color.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = title,
            style = Theme.textStyle.title.large,
            color = Theme.color.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Text(
            text = description,
            style = Theme.textStyle.body.large,
            color = Theme.color.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
        Image(
            painter = image,
            contentDescription = "Image",
            modifier = Modifier
                .width(107.dp)
                .height(100.dp)
        )

        Column(
            modifier = Modifier.padding()
        ) {
            TudeeTextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Theme.color.errorVariant)
                    .padding(vertical = 4.dp),
                onClickTextButton = onConfirmDelete,
                isEnabled = true,
                isLoading = false,
                isNegativeButton= true,

                ){
                Text(
                    text = stringResource(R.string.delete),
                    style = Theme.textStyle.label.large,
                    color = Theme.color.error,
                )
            }

            TudeeTextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp, bottom = 12.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Theme.color.onPrimary)
                    .border(
                        1.dp,
                        shape = RoundedCornerShape(100.dp),
                        color = Theme.color.stroke,
                    )
                    .padding(vertical = 4.dp),
                onClickTextButton = onCancel ,
                isEnabled = true,
                isLoading = false,
                isNegativeButton= false,
            ){
                Text(
                    text = stringResource(R.string.cancel),
                    style = Theme.textStyle.label.large,
                    color = Theme.color.primary,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ConfirmDeletePreview() {
    TudeeBottomSheet(isSheetOpen = true , onDismissRequest = {}) {
        ConfirmDelete(
            title = stringResource(R.string.are_you_sure),
            description = stringResource(R.string.this_action_cannot_be_undone),
            image = painterResource(id = R.mipmap.image_confirm_delete),
            onCancel = {},
            onConfirmDelete = {}
        )

    }

}