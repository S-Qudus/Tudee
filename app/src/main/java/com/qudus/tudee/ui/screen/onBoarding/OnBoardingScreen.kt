package com.qudus.tudee.ui.screen.onBoarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qudus.tudee.R
import com.qudus.tudee.ui.composable.TudeeFloatingActionButton
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.graphics.graphicsLayer
import java.util.Locale

@Composable
fun OnBoardingScreen(
    onBoardingViewModel: OnBoardingViewModel = koinViewModel(),
    navController: NavController
) {
    val uiState by onBoardingViewModel.uiState.collectAsState()
    OnBoardingContent(
        onBoardingInteractionListener = onBoardingViewModel,
        onBoardingUiState = uiState
    )


    LaunchedEffect(uiState.isCompleted) {
        if (uiState.isCompleted) {
            // navController.navigateToHome()
        }

    }
}

@Composable
private fun OnBoardingContent(
    onBoardingInteractionListener: OnBoardingInteractionListener,
    onBoardingUiState: OnBoardingUiState
) {
    val pagerState = rememberPagerState(
        pageCount = { onBoardingUiState.onBoardingItemUiState.size },
        initialPage = onBoardingUiState.currentPage
    )


    LaunchedEffect(onBoardingUiState.currentPage) {
        if (pagerState.currentPage != onBoardingUiState.currentPage) {
            pagerState.animateScrollToPage(onBoardingUiState.currentPage)
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { onBoardingInteractionListener.updatePage(it) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.overlay),
        contentAlignment = Alignment.BottomCenter
    ) {
        EllipseContent(onBoardingUiState)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            PagerContent(
                onBoardingUiState = onBoardingUiState,
                pagerState = pagerState,
                onBoardingInteractionListener = onBoardingInteractionListener
            )
            IndicatorPager(
                onBoardingUiState = onBoardingUiState,
                pagerState = pagerState
            )

        }
        Box(
            modifier = Modifier
                .size(350.dp)
                .offset(y = (-260).dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Theme.color.surfaceHigh,
                            Theme.color.surfaceHigh.copy(alpha = 0.1f)
                        ),
                        radius = 5f,

                        ),
                    shape = CircleShape,
                    alpha = 0.5f
                )
        )

    }
    SkipText(
        onBoardingUiState = onBoardingUiState,
        pagerState = pagerState,
        onBoardingInteractionListener = onBoardingInteractionListener
    )

}

@Composable
private fun EllipseContent(onBoardingUiState: OnBoardingUiState, modifier: Modifier = Modifier) {
    val ellipseBackgroundStartColor =
        if (onBoardingUiState.isDarkTheme) Color(0x7F05202E) else Color(0x7FEFF9FE)

    val ellipseBackgroundEndColor =
        if (onBoardingUiState.isDarkTheme) Color(0x0005202E) else Color(0x00EFF9FE)

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .offset(y = 90.dp)
                .rotate(135f)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(ellipseBackgroundStartColor, ellipseBackgroundEndColor),
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = 75f, y = 355f),

                        )
                ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .offset(x = 100.dp, y = 200.dp)
                .rotate(140f)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(ellipseBackgroundStartColor, ellipseBackgroundEndColor),
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = 75f, y = 355f),
                    )
                ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .offset(x = 200.dp, y = 360.dp)
                .rotate(140f)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(ellipseBackgroundStartColor, ellipseBackgroundEndColor),
                        start = Offset(x = 0f, y = 0f),
                        end = Offset(x = 75f, y = 355f),
                    )
                ),
        )
    }
}

@Composable
private fun PagerContent(
    onBoardingUiState: OnBoardingUiState,
    pagerState: PagerState,
    onBoardingInteractionListener: OnBoardingInteractionListener,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = pagerState,
    ) { pagerIndex ->
        Column(modifier = modifier) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .offset(y = 10.dp),
                    painter = painterResource(onBoardingUiState.onBoardingItemUiState[pagerIndex].imageRes),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.padding(top = 32.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(Theme.dimension.spacing16),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(192.dp)
                        .background(
                            color = Theme.color.onPrimaryCard,
                            shape = RoundedCornerShape(32.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Theme.color.onPrimaryStroke,
                            shape = RoundedCornerShape(32.dp)
                        )
                ) {
                    Column {
                        Text(
                            text = stringResource(onBoardingUiState.onBoardingItemUiState[pagerIndex].title),
                            style = Theme.textStyle.title.medium,
                            color = Theme.color.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(top = 24.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(onBoardingUiState.onBoardingItemUiState[pagerIndex].description),
                            style = Theme.textStyle.body.medium,
                            color = Theme.color.body,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(top = 32.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                }

                TudeeFloatingActionButton(
                    onClickIconButton = { onBoardingInteractionListener.onClickNextButton() },
                    isEnabled = true,
                    isLoading = false,
                    painter = painterResource(R.drawable.icon_arrow_right),
                    modifier = Modifier.offset(y = 90.dp)
                )

            }
        }

    }
}

@Composable
private fun SkipText(
    onBoardingUiState: OnBoardingUiState,
    pagerState: PagerState,
    onBoardingInteractionListener: OnBoardingInteractionListener,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(start = 16.dp, top = 64.dp),
        contentAlignment = Alignment.TopStart
    ) {
        if (pagerState.currentPage != onBoardingUiState.onBoardingItemUiState.lastIndex) {
            Text(
                text = stringResource(R.string.skip),
                style = Theme.textStyle.label.large,
                color = Theme.color.primary,
                modifier = Modifier
                    .clickable(
                        onClick = { onBoardingInteractionListener.onClickSkipText() },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
            )

        }
    }
}

@Composable
private fun IndicatorPager(
    onBoardingUiState: OnBoardingUiState,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp, top = 40.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        repeat(onBoardingUiState.onBoardingItemUiState.size) { index ->
            val isSelected = pagerState.currentPage == index
            val dividerColor by animateColorAsState(if (isSelected) Theme.color.primary else Theme.color.primaryVariant)

            Box(
                modifier = Modifier
                    .size(height = 5.dp, width = 100.dp)
                    .background(
                        color = dividerColor,
                        shape = CircleShape
                    )
            )

            if (index < 2) {
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            }

        }
    }
}

@Composable
@Preview(showBackground = false, showSystemUi = true)
fun OnBoardingContentPreview() {
    val pages = listOf(
        OnBoardingItemUiState(
            title = R.string.overwhelmed_with_tasks,
            description = R.string.let_s_bring_some_order_to_the_chaos_tudee_is_here_to_help_you_sort_plan_and_breathe_easier,
            imageRes = R.drawable.image_happy_tudee_onboarding
        ),
        OnBoardingItemUiState(
            title = R.string.uh_oh_procrastinating_again,
            description = R.string.tudee_not_mad_just_a_little_disappointed_let_s_get_back_on_track_together,
            imageRes = R.drawable.image_tudee_onboarding_two
        ),
        OnBoardingItemUiState(
            title = R.string.let_s_complete_task_and_celebrate_together,
            description = R.string.progress_is_progress_tudee_will_celebrate_you_on_for_every_win_big_or_small,
            imageRes = R.drawable.image_tudee_onbaring_three
        ),
    )
    TudeeTheme(false) {
        OnBoardingContent(
            onBoardingInteractionListener = object : OnBoardingInteractionListener {
                override fun onClickSkipText() {

                }

                override fun onClickNextButton() {
                }

                override fun updatePage(index: Int) {
                }

            },
            onBoardingUiState = OnBoardingUiState(onBoardingItemUiState = pages)
        )
    }

}