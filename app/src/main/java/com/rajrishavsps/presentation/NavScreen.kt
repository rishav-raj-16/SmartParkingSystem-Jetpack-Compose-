package com.rajrishavsps.presentation

sealed class NavScreen(val rout: String) {

    object LoadingScreen: NavScreen("loading")
    object SlotsScreen: NavScreen("slots")
}