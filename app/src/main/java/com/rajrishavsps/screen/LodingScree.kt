package com.rajrishavsps.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerState
import com.rajrishavsps.presentation.MapState
import com.rajrishavsps.presentation.NavScreen
import com.rajrishavsps.utils.getCurrentLocation


@Composable
fun LoadingScreen(context: Context, navController: NavController) {
    var showMap by remember {
        mutableStateOf(false)
    }
    var location by remember {
        mutableStateOf(LatLng(0.0, 0.0))
    }
    val mapProperties = MapState().properties

    getCurrentLocation(context) {
        location = it
        showMap = true
    }

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false);
    }

    if (showMap) {
        MapScreen(
            context = context,
            latlang = location,
            mapProperties = mapProperties,
            uiSettings = uiSettings,
            navController = navController,
        )
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading Maps....")
        }
    }

}