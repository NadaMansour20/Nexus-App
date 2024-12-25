package com.training.graduation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.graduation.R


@Preview(showSystemUi = true)
@Composable
fun ScheduleMeeting() {
    var isScheduleVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        CalendarScreen(onAddEventClick = { isScheduleVisible = true })

        if (isScheduleVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Schedule_Card(onClose = { isScheduleVisible = false })
            }
        }
    }
}

@Composable
fun CalendarScreen(onAddEventClick: () -> Unit) {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF000000).copy(alpha = 0.7f),
                        Color(0xFF0D47A1)
                    )
                )
            )
    ) {
        // Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            // You can add some header content here
        }

        Card(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight(0.42f),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(
                topStart = 40.dp,
                topEnd = 40.dp,
                bottomEnd = 40.dp,
                bottomStart = 40.dp
            ),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            //CalendarView()  // Add your calendar view here if needed
        }

        // Today's Events
        Text(
            text = stringResource(R.string.meetings_scheduled),
            modifier = Modifier.padding(16.dp),
            style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(listOf("Meeting about KRR", "Team Meeting", "Project Discussion")) { meeting ->
                MeetingCard(meetingName = meeting, description = "Description for $meeting")
            }
        }

        // Floating Action Button
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            FloatingActionButton(
                onClick = onAddEventClick,
                containerColor = colorResource(R.color.orange),
                contentColor = Color(0xFFFFFFFF)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Event")
            }
        }
    }
}

@Composable
fun MeetingCard(meetingName: String, description: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White.copy(alpha = 0.15f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = meetingName,
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(
                    text = description,
                    style = TextStyle(color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = "3:00 PM",
                    style = TextStyle(color = Color.White.copy(alpha = 0.5f), fontSize = 14.sp)
                )
            }
        }
    }
}

