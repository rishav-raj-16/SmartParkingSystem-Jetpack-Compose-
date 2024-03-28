package com.rajrishavsps.presentation

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings

data class MapState(
    val properties: MapProperties = MapProperties(),
    val isFalloutMap : Boolean = false,
)
