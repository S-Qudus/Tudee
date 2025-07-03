package com.qudus.tudee.ui.screen

sealed class Screens(val route: String){
    object OnBoardingScreen: Screens("OnBoardingScreen")
}