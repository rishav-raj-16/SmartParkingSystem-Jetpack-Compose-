package com.rajrishavsps.screen

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rajrishavsps.R
import com.rajrishavsps.presentation.MapsViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    navController: NavController, slot: Int, viewModel: MapsViewModel
) {

    var name by remember {
        mutableStateOf("")
    }

    var sliderPosition by remember {
        mutableFloatStateOf(10f)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val focusRequester = remember {
        FocusRequester()
    }
    val view = LocalView.current

    if (!showDialog) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ), title = { Text(text = "BOOK SLOT") }, navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                })
            },

            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Amount to be Pay", fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "\u20B9 ${sliderPosition * 10}",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 40.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Button(
                            onClick = {
                                if (name.isBlank()) {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Please enter your name")
                                    }
                                } else {
                                    viewModel.bookSlot(slot, (sliderPosition * 1000).toLong())
                                    showDialog = !showDialog
                                }
                            },
                            contentPadding = PaddingValues(vertical = 20.dp, horizontal = 60.dp),
                            modifier = Modifier.background(
                                shape = RoundedCornerShape(5.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = "PAY NOW", fontSize = 20.sp
                            )
                        }
                    }
                }
            },

            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { paddingValue ->
            Surface(
                modifier = Modifier
                    .padding(paddingValue)
                    .padding(start = 10.dp, end = 10.dp),
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.car),
                        contentDescription = "Car Image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "BooK Now \uD83D\uDE0A",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                    Divider(color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.padding(top = 10.dp))


                    OutlinedTextField(
                        label = {
                            Text(
                                text = "Enter your name",
                                color = MaterialTheme.colorScheme.primary
                            )
                        },
                        value = name,
                        onValueChange = { name = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }, colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.primary
                        ),
                        singleLine = true,
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusRequester.requestFocus()
                                val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

                                inputManager.hideSoftInputFromWindow(view.windowToken, 0)
                            }
                        ),
                        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester = focusRequester)
                    )

                    Spacer(modifier = Modifier.padding(top = 10.dp))

                    InputTime(sliderPosition) {
                        sliderPosition = it
                    }

                    Spacer(modifier = Modifier.padding(top = 10.dp))

                    YourSlot(slot = "A-$slot")

                    Spacer(modifier = Modifier.padding(top = 10.dp))
                }
            }
        }

    } else {
        DoneDialog(navController = navController, price = (sliderPosition * 10).toString())
    }
}


@Composable
fun InputTime(currentPosition: Float, onPositionChange: (Float) -> Unit) {

    Column {
        Text(text = "Choose Slot Time(in Minuets)")

        Spacer(modifier = Modifier.padding(5.dp))


        Slider(
            value = currentPosition,
            onValueChange = onPositionChange,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.primaryContainer
            ),
            steps = 4,
            valueRange = 10f..60f,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 10..60 step 10) {
                Text(text = "$i")
            }
        }
    }
}


@Composable
fun YourSlot(slot: String) {


    Column {
        Text(text = "Your Slot Number")

        Spacer(modifier = Modifier.padding(10.dp))

        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.background(
                color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp)
            )
        ) {
            Text(
                modifier = Modifier.padding(30.dp),
                text = slot,
                fontSize = 50.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun DoneDialog(navController: NavController, price: String) {
    AlertDialog(
        onDismissRequest = { navController.popBackStack() },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "SLOT BOOKED",
                    fontWeight = FontWeight.Bold,
                )
            }

        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.done),
                    contentDescription = "Done Image",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "Your Slot Booked", fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = price, fontSize = 40.sp, fontWeight = FontWeight.SemiBold
                )
            }
        },
        confirmButton = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Red)
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Done")
                }
            }
        },
        titleContentColor = MaterialTheme.colorScheme.primary,
        textContentColor = MaterialTheme.colorScheme.primary,
    )
}