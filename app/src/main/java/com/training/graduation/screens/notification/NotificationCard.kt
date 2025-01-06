package com.training.graduation.screens.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun MeetingNotification(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomEnd = 10.dp,
            bottomStart = 10.dp
        ),
    ) {
        Column {

            Row(
                modifier = Modifier.padding(20.dp)
            ) {
                Text("Title........", maxLines = 1)
            }
            Row (
                modifier = Modifier.padding(start = 20.dp)

            ){
                Text("Description.....................................",
                    maxLines = 2)


            }
            Spacer(modifier = Modifier.padding(bottom = 20.dp))

            Row {
                Spacer(modifier = Modifier.padding(100.dp))

                Text("2/12/2024")
                Spacer(modifier = Modifier.padding(10.dp))

                Text("2:00 PM")

            }
        }
    }

}