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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rajrishavsps.presentation.MapsViewModel
import com.rajrishavsps.presentation.NavScreen
import com.rajrishavsps.screen.BookingScreen
import com.rajrishavsps.screen.LoadingScreen
import com.rajrishavsps.screen.LocationPermissionScreen
import com.rajrishavsps.screen.SlotsScreen
import com.rajrishavsps.ui.theme.SPSTheme
import com.rajrishavsps.utils.checkForPermission

class MainActivity : ComponentActivity() {

    private val viewModel = MapsViewModel()

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

                    // Track location permission state
                    var hasLocationPermission by remember { mutableStateOf(checkForPermission(this)) }

                    NavHost(
                        navController = navController,
                        startDestination = if (hasLocationPermission) NavScreen.LoadingScreen.rout else NavScreen.LocationPermissionScreen.rout
                    ) {
                        composable(NavScreen.LoadingScreen.rout) {
                            LoadingScreen(
                                context = this@MainActivity,
                                navController = navController
                            )
                        }

                        composable(NavScreen.SlotsScreen.rout) {
                            SlotsScreen(navController = navController,viewModel = viewModel)
                        }

                        composable(NavScreen.BookingScreen.rout + "/{slot}", arguments = listOf(
                            navArgument("slot") {
                                type = NavType.IntType
                            }
                        )) { backStackEntry ->

                            val slot = backStackEntry.arguments?.getInt("slot") ?: 0
                            BookingScreen(
                                navController = navController,
                                slot = slot,
                                viewModel = viewModel
                            )
                        }

                        composable(NavScreen.LocationPermissionScreen.rout) {
                            LocationPermissionScreen {
                                hasLocationPermission = true
                                navController.navigate(NavScreen.LoadingScreen.rout)
                            }
                        }
                    }
                }
            }
        }
    }
}