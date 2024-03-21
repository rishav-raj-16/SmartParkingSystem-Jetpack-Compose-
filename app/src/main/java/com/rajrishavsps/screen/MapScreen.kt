package com.rajrishavsps.screen

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.rajrishavsps.presentation.MapEvent
import com.rajrishavsps.presentation.MapsViewModel


@Composable
fun MapScreen(
    viewModel: MapsViewModel = MapsViewModel(),
    context: Context,
    latlang: LatLng,
    mapProperties: MapProperties,
    uiSettings: MapUiSettings
) {

    val latlangList = remember {
        mutableStateListOf(latlang)
    }

    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(latlang,15f)
    }


    Scaffold(
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
    ) {padding ->
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
                onMapClick = {
                             latlangList.add(it)
                },
            ){
                latlangList.forEach{
                    Marker(
                        state = MarkerState(position = it),
                        title = "Location",
                    )
                }
            }
        }
    }
}
