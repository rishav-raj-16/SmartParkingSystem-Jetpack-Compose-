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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rajrishavsps.ui.theme.LightBlue

@Preview(showSystemUi = true)
@Composable
fun SlotsList() {

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
                SingleSlot(modifier = Modifier.weight(1f), slotNumber = i)
                Spacer(modifier = Modifier.padding(5.dp))
                VerticalDivider(
                    modifier = Modifier.height(100.dp),
                    thickness = 2.dp,
                    color = Color.Blue
                )
                Spacer(modifier = Modifier.padding(5.dp))
                SingleSlot(modifier = Modifier.weight(1f), slotNumber = i + 1)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}



@Composable
fun SingleSlot(modifier: Modifier = Modifier, slotNumber: Int = 0) {
    Box(
        modifier = modifier
            .height(150.dp)
            .border(2.dp, LightBlue, RoundedCornerShape(2.dp))
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
                    .border(width = 1.dp, color = LightBlue, RoundedCornerShape(10.dp))
                    .padding(start = 10.dp, end = 10.dp)
            )

            Button(
                modifier = Modifier
                    .height(30.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Blue),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(start = 30.dp, end = 30.dp),
            ) {
                Text(
                    text = "BOOK",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}