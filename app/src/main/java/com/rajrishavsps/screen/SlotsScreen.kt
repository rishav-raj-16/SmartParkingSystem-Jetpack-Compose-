package com.rajrishavsps.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rajrishavsps.presentation.MapsViewModel
import com.rajrishavsps.presentation.NavScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlotsScreen(navController: NavController, viewModel: MapsViewModel) {

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = White
                ),
                title = {
                    Text(
                        text = "Parking Slots"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValue ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(paddingValue)
                .padding(top = 2.dp)
        ) {
            Column {

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Parking Slots",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = FontFamily.Serif,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
                    )
                }

                Text(
                    text = "Choose the Parking Slot..",
                    modifier = Modifier.padding(bottom = 20.dp, start = 5.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                SlotsList(navController, viewModel)
            }
        }
    }
}


@Composable
fun SlotsList(navController: NavController, viewModel: MapsViewModel) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 1..20 step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SingleSlot(
                    modifier = Modifier.weight(1f),
                    slotNumber = i,
                    navController = navController,
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.padding(5.dp))
                VerticalDivider(
                    modifier = Modifier.height(100.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(5.dp))
                SingleSlot(
                    modifier = Modifier.weight(1f),
                    slotNumber = i + 1,
                    navController = navController,
                    viewModel = viewModel
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun SingleSlot(
    modifier: Modifier = Modifier,
    slotNumber: Int = 0,
    navController: NavController,
    viewModel: MapsViewModel
) {

    val isBooked = slotNumber in viewModel.bookedList

    Box(
        modifier = modifier
            .padding(start = 5.dp, end = 5.dp)
            .height(150.dp)
            .border(2.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(2.dp))
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "A-$slotNumber",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.tertiary,
                        RoundedCornerShape(10.dp)
                    )
                    .padding(start = 10.dp, end = 10.dp)
            )


            Button(
                modifier = Modifier
                    .height(30.dp),
                onClick = {
                    if (!isBooked) {
                        navController.navigate(NavScreen.BookingScreen.rout + "/$slotNumber")
                    }
                },
                colors = ButtonDefaults.buttonColors(if (isBooked) Red else MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(start = 30.dp, end = 30.dp),
            ) {

                Text(
                    text = if (isBooked) "Booked" else "Book",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}