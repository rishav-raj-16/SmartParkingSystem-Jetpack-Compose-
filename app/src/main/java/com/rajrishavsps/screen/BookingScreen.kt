package com.rajrishavsps.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rajrishavsps.R

//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp


@Preview(showSystemUi = true)
@Composable
fun BookingScreen() {

    Scaffold(

    ) { paddingValue ->
        Surface(
            modifier = Modifier.padding(paddingValue)
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.car),
                    contentDescription = "Car Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(text = "BooK Now")
            }
        }
    }

}