package com.rajrishavsps.presentation

sealed class NavScreen(val rout: String) {

    object LoadingScreen: NavScreen("loading")
    object SlotsScreen: NavScreen("slots")
    object BookingScreen: NavScreen("booking")
    object LocationPermissionScreen: NavScreen("permission")
}