package com.training.graduation.screens


import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.training.graduation.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun Schedule_Card(onClose: () -> Unit){

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
        shape = RoundedCornerShape(
            topStart = 40.dp,
            topEnd = 40.dp,
            bottomStart = 40.dp,
            bottomEnd = 40.dp
        ),
        colors = CardDefaults.cardColors(Color.White)
    ){
        //scroll vertical
        val scrollState = rememberScrollState()

        Column(modifier = Modifier
            .verticalScroll(scrollState)
        )  {
            ScheduleInputs(onClose)
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleInputs(onClose: () -> Unit) {

    val context = LocalContext.current



    var selectedDate by remember { mutableStateOf("") }

    var meetingName by remember { mutableStateOf("") }
    var meetingDescription by remember { mutableStateOf("") }


    var meetingNameError by remember { mutableStateOf<String?>(null) }
    var meetingDescriptionError by remember { mutableStateOf<String?>(null) }

    var selectDateError by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp)
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

        Spacer(modifier = Modifier.padding(top = 40.dp))

        Text(
            stringResource(R.string.date_and_time),
            style = TextStyle(fontSize = 20.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold),
        )


        OutlinedTextField(
            value =selectedDate,
            onValueChange = {
                selectedDate = it
                selectDateError = if (it.isEmpty()) context.getString(R.string.date_is_required) else null
            },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            ),
            label = { Text(stringResource(R.string.select_date)) },
            isError =selectDateError != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            maxLines = 4,
            singleLine = false,

            trailingIcon = {
                IconButton(onClick = {
                    ShowDatePicker(context) { date ->

                        val selectedDateObject = Date(date)

                        val selectedCalendar = Calendar.getInstance().apply {
                            time = selectedDateObject
                        }

                        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault())
                            .format(Calendar.getInstance().time)

                        val dateFormat = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault())
                        selectedDate = "${dateFormat.format(selectedCalendar.time)} $currentTime"
                    }
                })
                {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Date"
                    )
                }
            },
            readOnly = true,
        )
        if (selectDateError != null) {
            Text(
                text = selectDateError ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.padding(top = 40.dp))

        Text(
            stringResource(R.string.meeting_description),
            style = TextStyle(fontSize = 20.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold),
        )

        // TextInputLayout لـ Description
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


fun ShowDatePicker(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            onDateSelected(selectedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}


@Composable
fun Button_Save_Schedule(onClose: () -> Unit) {
    Row(

    ) {
        Button(onClick = {}) {
            Text(stringResource(R.string.Save))
        }

        Spacer(modifier = Modifier.padding(start = 10.dp))

        Button(onClick = onClose) {
            Text(stringResource(R.string.Cancel))
        }
    }
}

