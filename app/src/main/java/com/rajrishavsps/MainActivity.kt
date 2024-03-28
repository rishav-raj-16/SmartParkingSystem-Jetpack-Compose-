package com.rajrishavsps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rajrishavsps.presentation.NavScreen
import com.rajrishavsps.screen.LoadingScreen
import com.rajrishavsps.screen.LocationPermissionScreen
import com.rajrishavsps.screen.SlotsScreen
import com.rajrishavsps.ui.theme.SPSTheme
import com.rajrishavsps.utils.checkForPermission
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = NavScreen.LoadingScreen.rout
                    ) {
                        composable(NavScreen.LoadingScreen.rout) {
                            LoadingScreen(
                                context = this@MainActivity,
                                navController = navController
                            )
                        }

                        composable(NavScreen.SlotsScreen.rout) {
                            SlotsScreen()
                        }
                    }

                    var hasLocationPermission by remember {
                        mutableStateOf(checkForPermission(this))
                    }

                    if (hasLocationPermission) {
                        LoadingScreen(context = this, navController = navController)
                    } else {
                        LocationPermissionScreen {
                            hasLocationPermission = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SliderWithMarks(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Slider(
            value = value,
            valueRange = valueRange,
            onValueChange = onValueChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
            for (i in valueRange.start.toInt()..valueRange.endInclusive.toInt()) {
                val tickPosition =
                    (i - valueRange.start) / (valueRange.endInclusive - valueRange.start) * modifier.size.width
                Box(
                    modifier = Modifier
                        .size(1.dp, 8.dp)
                        .align(Alignment.Bottom)
                        .offset(x = tickPosition)
                        .background(Color.Gray)
                )
                if (i != valueRange.start.toInt() && i != valueRange.endInclusive.toInt()) {
                    Text(
                        text = i.toString(),
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .offset(x = tickPosition)
                    )
                }
            }
        }
    }
}


