package com.rajrishavsps.presentation

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings


@Composable
fun MapScreen(
    viewModel: MapsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false);
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
    ) { innerpadding ->
        Surface(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            GoogleMap(
                properties = viewModel.state.properties,
                uiSettings = uiSettings,
                onMapClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
