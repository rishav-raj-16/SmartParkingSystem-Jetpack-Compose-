package com.rajrishavsps.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsViewModel : ViewModel() {
    var state by mutableStateOf(MapState())


    fun onEvent(event: MapEvent) {
        when (event) {
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

    private val database = Firebase.database.reference.child("booking")

    fun bookSlot(slotNumber: Int, name: String, duration: Long) {
        database.child(slotNumber.toString()).child("Duration").setValue(duration)
        database.child(slotNumber.toString()).child("Name").setValue(name)

        viewModelScope.launch {
            delay(duration)
            unbookSlot(slotNumber)
        }
    }

    fun isSlotBooked(slotNumber: Int, callback: (Boolean) -> Unit) {
        // Query Firebase to check if the slot is booked
        database.child(slotNumber.toString()).get().addOnSuccessListener { dataSnapshot ->
            // Check if the dataSnapshot exists
            val isBooked = dataSnapshot.exists()
            callback(isBooked)
        }.addOnFailureListener { exception ->
            Log.e(TAG, "Error checking if slot is booked: $exception")
            callback(false)
        }
    }

    fun unbookSlot(slotNumber: Int) {
        database.child(slotNumber.toString()).removeValue()
    }
}

