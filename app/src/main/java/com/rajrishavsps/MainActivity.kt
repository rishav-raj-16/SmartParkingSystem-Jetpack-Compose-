package com.rajrishavsps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.rajrishavsps.screen.LoadingScreen
import com.rajrishavsps.screen.LocationPermissionScreen
import com.rajrishavsps.screen.MapScreen
import com.rajrishavsps.screen.SlotsList
import com.rajrishavsps.ui.theme.SPSTheme
import com.rajrishavsps.utils.checkForPermission

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SPSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    var hasLocationPermission  by remember {
//                        mutableStateOf(checkForPermission(this))
//                    }
//
//                    if (hasLocationPermission) {
//                        LoadingScreen(context = this)
//                    } else {
//                        LocationPermissionScreen {
//                            hasLocationPermission = true
//                        }
//                    }

                    SlotsList()
                }
            }
        }
    }
}


