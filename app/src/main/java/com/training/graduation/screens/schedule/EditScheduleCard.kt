package com.training.graduation.screens.schedule

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.graduation.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Schedule_Card(onClose: () -> Unit) {
    Edit_schedule(onClose)
}

@Composable
fun Edit_schedule(onClose: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 80.dp, bottom = 80.dp, start = 20.dp, end = 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(40.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            ScheduleInputs(onClose)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleInputs(onClose: () -> Unit) {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var meetingName by remember { mutableStateOf("") }
    var meetingDescription by remember { mutableStateOf("") }
    var meetingNameError by remember { mutableStateOf<String?>(null) }
    var meetingDescriptionError by remember { mutableStateOf<String?>(null) }
    var selectDateError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            stringResource(R.string.meeting_title),
            style = TextStyle(fontSize = 20.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold),
        )

        OutlinedTextField(
            value = meetingName,
            onValueChange = {
                meetingName = it
                meetingNameError = if (it.isEmpty()) context.getString(R.string.meeting_name_is_required) else null
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            ),
            label = { Text(stringResource(R.string.meeting_name)) },
            isError = meetingNameError != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        if (meetingNameError != null) {
            Text(
                text = meetingNameError ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.padding(top = 25.dp))

        Text(
            stringResource(R.string.date_and_time),
            style = TextStyle(fontSize = 20.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold),
        )

        // Date and Time Inputs
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            // Date Picker
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { },
                    shape = RoundedCornerShape(16.dp),
                    label = { Text(stringResource(R.string.select_date)) },
                    trailingIcon = {
                        IconButton(onClick = {
                            ShowDatePicker(context) { date ->
                                selectedDate = date
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = stringResource(R.string.select_date)
                            )
                        }
                    },
                    readOnly = true,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Time Picker
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = selectedTime,
                    onValueChange = { },
                    shape = RoundedCornerShape(16.dp),
                    label = { Text(stringResource(R.string.select_time)) },
                    trailingIcon = {
                        IconButton(onClick = {
                            ShowTimePicker(context) { time ->
                                selectedTime = time
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "select_time"
                            )
                        }
                    },
                    readOnly = true,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 25.dp))

        Text(
            stringResource(R.string.meeting_description),
            style = TextStyle(fontSize = 20.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold),
        )

        OutlinedTextField(
            value = meetingDescription,
            onValueChange = {
                meetingDescription = it
                meetingDescriptionError = if (it.isEmpty()) context.getString(R.string.description_is_required) else null
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            ),
            label = { Text(stringResource(R.string.description)) },
            isError = meetingDescriptionError != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .padding(vertical = 8.dp),
            maxLines = 4,
            singleLine = false,
        )
        if (meetingDescriptionError != null) {
            Text(
                text = meetingDescriptionError ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button_Save_Schedule(onClose)
    }
}

// DatePicker Function
fun ShowDatePicker(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val dateFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())
            val selectedCalendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            onDateSelected(dateFormat.format(selectedCalendar.time))
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

// TimePicker Function
fun ShowTimePicker(context: Context, onTimeSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    android.app.TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            val isPM = hourOfDay >= 12
            val hour12Format = if (hourOfDay % 12 == 0) 12 else hourOfDay % 12
            val selectedTime = String.format(
                "%02d:%02d %s",
                hour12Format,
                minute,
                if (isPM) "PM" else "AM"
            )
            onTimeSelected(selectedTime)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    ).show()
}

@Composable
fun Button_Save_Schedule(onClose: () -> Unit) {
    Row {
        Button(onClick = {}) {
            Text(stringResource(R.string.Save))
        }
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Button(onClick = onClose) {
            Text(stringResource(R.string.Cancel))
        }
    }
}
