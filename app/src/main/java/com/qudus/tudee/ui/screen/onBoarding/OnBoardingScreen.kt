package com.qudus.tudee.ui.screen.onBoarding

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    ) {
        val ellipseBackground =
            if (onBoardingUiState.isDarkTheme) R.drawable.ellipse_background_dark else R.drawable.ellipse_background
        Image(
            painter = painterResource(ellipseBackground),
            modifier = Modifier.offset(x = (30).dp),
            contentDescription = "ellipse_background"
        )


        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                state = pagerState,
            ) { pagerIndex ->
                Column {
                    Log.d("uiState 123123", "$onBoardingUiState")

                    Spacer(modifier = Modifier.padding(86.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(width = 296.dp, height = 250.dp)
                                .offset(y = 30.dp),
                            painter = painterResource(onBoardingUiState.onBoardingItemUiState[pagerIndex].imageRes),
                            contentDescription = ""
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .padding(Theme.dimension.medium),
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, top = 32.dp),
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
        Box(modifier = Modifier.padding(start = 16.dp, top = 64.dp)) {
            if (pagerState.currentPage != onBoardingUiState.onBoardingItemUiState.lastIndex) {
                Text(
                    text = stringResource(R.string.skip),
                    style = Theme.textStyle.label.large,
                    color = Theme.color.primary,
                    modifier = Modifier
                        .clickable {
                            onBoardingInteractionListener.onClickSkipText()
                        }
                )

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