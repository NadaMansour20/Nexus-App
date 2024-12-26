package com.training.graduation.screens


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartMeeting() {
    var showDialog by remember { mutableStateOf(false) }

    // Main content of the StartMeeting screen
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Open Settings", fontSize = 18.sp)
        }

        // Show the dialog on the same page
        if (showDialog) {
            QuestionsDialog(onDismissRequest = { showDialog = false })
        }
    }
}

@Composable
fun QuestionsDialog(onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text("Settings", fontSize = 20.sp)
        },
        text = {
            SwitchScreen(modifier = Modifier.fillMaxWidth())
        },
        confirmButton = {
            Button(onClick = onDismissRequest) {
                Text("OK")
            }
        },
        containerColor = Color.White,
        textContentColor = Color.Black
    )
}

@Composable
fun SwitchScreen(modifier: Modifier = Modifier) {
    var isCameraEnabled by remember { mutableStateOf(false) }
    var isMicEnabled by remember { mutableStateOf(false) }
    var isRecordEnabled by remember { mutableStateOf(false) }
    var isReportEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SwitchRow(
            text = "Access Camera",
            checked = isCameraEnabled,
            onCheckedChange = { isCameraEnabled = it }
        )
        SwitchRow(
            text = "Access Microphone",
            checked = isMicEnabled,
            onCheckedChange = { isMicEnabled = it }
        )
        SwitchRow(
            text = "Create Form",
            checked = isRecordEnabled,
            onCheckedChange = { isRecordEnabled = it }
        )
        SwitchRow(
            text = "Allow Chatbot",
            checked = isReportEnabled,
            onCheckedChange = { isReportEnabled = it }
        )
    }
}

@Composable
fun SwitchRow(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Blue,
                uncheckedThumbColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartMeetingPreview() {
    StartMeeting()
}