package com.rajrishavsps.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.MapStyleOptions
import kotlinx.coroutines.CoroutineScope

class MapsViewModel : ViewModel() {
    var state by mutableStateOf(MapState())

    var bookedSlots = mutableMapOf<Int,Int>()

    fun onEvent(event: MapEvent) {
        when(event){
            is MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.isFalloutMap) null else MapStyleOptions(MapStyle.json)
                    ),
                    isFalloutMap = !state.isFalloutMap
                )
            }
        }
    }

    fun bookSlot(slotNumber: Int, duration: Int) {
        bookedSlots[slotNumber] = duration
    }

    fun unbookSlot(slotNumber: Int) {
        bookedSlots.remove(slotNumber)
    }

}

