package com.rajrishavsps.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.rajrishavsps.presentation.MapEvent
import com.rajrishavsps.presentation.MapsViewModel
import com.rajrishavsps.presentation.NavScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapsViewModel = MapsViewModel(),
    context: Context,
    latlang: LatLng,
    mapProperties: MapProperties,
    uiSettings: MapUiSettings,
    navController: NavController,
) {

    val latlangList = remember {
        mutableStateListOf(latlang)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latlang, 15f)
    }

    var showSlots by remember {
        mutableStateOf(false)
    }

    fun onMarkerClicked(marker: Marker): Boolean {
        showSlots = true
        return true
    }


    if (!showSlots) {

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    title = { Text(text = "Smart Parking System") },
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(MapEvent.ToggleFalloutMap)
                    }) {
                    Icon(
                        imageVector = if (viewModel.state.isFalloutMap) Icons.Default.WbSunny else Icons.Default.NightsStay,
                        contentDescription = null
                    )
                }
            }
        ) { padding ->
            Surface(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.primary
            ) {
                GoogleMap(
                    properties = viewModel.state.properties,
                    uiSettings = uiSettings,
                    cameraPositionState = cameraPositionState,
                    modifier = Modifier.fillMaxSize(),

                    ) {
                    latlangList.forEach {
                        Marker(
                            state = MarkerState(position = it),
                            title = "Location",
                            onClick = { marker ->
                                onMarkerClicked(marker)
                            }
                        )
                    }
                }

            }
        }
    } else {
        navController.navigate(NavScreen.SlotsScreen.rout)
    }

}
