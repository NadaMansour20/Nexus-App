package com.training.graduation.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.graduation.R


@Preview
@Composable
fun RecordedMeeting(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomEnd = 10.dp,
            bottomStart = 10.dp
        ),
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){

            Row(
                modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val image = painterResource(id = R.drawable.ic_video)

                Image(
                    painter = image,
                    contentDescription = "Circular Image",
                    modifier = Modifier.size(45.dp)

                )

                Column {

                    Text("Title........", maxLines = 1,
                        modifier = Modifier.padding(start = 10.dp))

                    Spacer(modifier = Modifier.padding( 2.dp))

                    Text("10 MB",modifier = Modifier.padding(start = 10.dp))
                }
                Spacer(modifier = Modifier.padding( 100.dp))


                Menu()


            }
        }
    }

}
@Composable
fun Menu() {
    var expanded by remember { mutableStateOf(false) }


    Box() {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Menu"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            DropdownMenuItem(
                text = { Text("Open", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )

            DropdownMenuItem(
                text = { Text("Download", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Delete", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Share", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Copy link", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )
        }
    }
}

//
//if (showRecordings) {
//    val context = LocalContext.current
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Transparent)
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter)
//                .padding(16.dp)
//                .background(Color.White)
//
//        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                itemsIndexed(recordingsList) { index, recording ->
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp)
//                    ) {
////                                    Text(
////                                        text = recording.filename,
////                                        modifier = Modifier.weight(1f)
////                                    )
//                        Text(
//                            text = recording.startTime.toString(),
//                            modifier = Modifier.weight(1f),
//                            textAlign = TextAlign.End
//                        )
//                        Text(
//                            text = recording.endTime.toString(),
//                            modifier = Modifier.weight(1f),
//                            textAlign = TextAlign.End
//                        )
//                        ClickableText(
//                            text = buildAnnotatedString {
//                                append(recording.filename)
//                            },
//                            onClick = { offset ->
//                                // عند الضغط على النص، قم بفتح الرابط أو ما ترغب به
//                                val url = recording.url// أو يمكنك استخدام رابط التسجيل مثل recording.url
//                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                                try {
//                                    context.startActivity(intent)
//                                } catch (e: Exception) {
//                                    Toast.makeText(context, "No application to handle this action", Toast.LENGTH_SHORT).show()
//                                }
//                            },
//                            modifier = Modifier.weight(1f)
//                        )
////                                    IconButton(onClick = {
////                                        lifecycleScope.launch {
////                                            call.deleteRecording(recordingId = recording.id) // Assuming CallRecording has an id property
////                                                .onSuccess {
////                                                    // Remove the recording from the list
////                                                    recordingsList.remove(recording)
////                                                }
////                                                .onError { error ->
////                                                    Toast.makeText(context, "Error deleting recording: ${error.message}", Toast.LENGTH_SHORT).show()
////                                                }
////                                        }
////                                    }) {
////                                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Recording")
////                                    }
//
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//}
//}
//}