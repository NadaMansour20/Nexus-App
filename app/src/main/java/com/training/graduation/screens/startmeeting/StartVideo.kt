package com.training.graduation.screens.startmeeting
import android.content.Intent
import android.media.metrics.RecordingSession
import android.media.tv.TvInputService
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.training.graduation.R


import io.getstream.video.android.compose.permission.LaunchCallPermissions
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent
import io.getstream.video.android.compose.ui.components.call.controls.ControlActions
import io.getstream.video.android.compose.ui.components.call.controls.actions.FlipCameraAction
import io.getstream.video.android.compose.ui.components.call.controls.actions.ToggleCameraAction
import io.getstream.video.android.compose.ui.components.call.controls.actions.ToggleMicrophoneAction
import io.getstream.video.android.compose.ui.components.call.renderer.FloatingParticipantVideo
import io.getstream.video.android.compose.ui.components.call.renderer.ParticipantVideo
import io.getstream.video.android.core.GEO
import io.getstream.video.android.core.RealtimeConnection
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import kotlinx.coroutines.launch
import org.openapitools.client.models.CallRecording


class StartVideo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiKey = "mmhfdzb5evj2"
        val userToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL3Byb250by5nZXRzdHJlYW0uaW8iLCJzdWIiOiJ1c2VyL0plcmVjIiwidXNlcl9pZCI6IkplcmVjIiwidmFsaWRpdHlfaW5fc2Vjb25kcyI6NjA0ODAwLCJpYXQiOjE3MzU5NzczODAsImV4cCI6MTczNjU4MjE4MH0.zgb3en28mlhP1kDeAJWvOZ_SdKtfoTdqfKV7UPTkU7c"
        val userId = "Jerec"
        val callId = "U4sxudQfSkVK"

        val user = User(
            id = userId,
            name = "Tutorial",
            image = "https://bit.ly/2TIt8NR",
        )

        val client = StreamVideoBuilder(
            context = applicationContext,
            apiKey = apiKey,
            geo = GEO.GlobalEdgeNetwork,
            user = user,
            token = userToken,
        ).build()

        setContent {
            val call = client.call(type = "default", id = callId)
            val isRecording by call.state.recording.collectAsStateWithLifecycle()
            val recordingsList = remember { mutableStateListOf<CallRecording>() }
            var showRecordings by remember { mutableStateOf(false) }

            // Observe recording state changes
            LaunchedEffect(isRecording) {
                if (isRecording) {
                    // Recording started, fetch recordings and update the list
                    call.listRecordings()
                        .onSuccess { response ->
                            recordingsList.clear()
                            recordingsList.addAll(response.recordings)

                        }
                        .onError { error ->
                            Toast.makeText(
                                applicationContext,
                                "Error: ${error.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }

            LaunchCallPermissions(
                call = call,
                onAllPermissionsGranted = {
                    val result = call.join(create = true)
                    result.onError {
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            )

            VideoTheme {
                val isCameraEnabled by call.camera.isEnabled.collectAsState()
                val isMicrophoneEnabled by call.microphone.isEnabled.collectAsState()

                CallContent(
                    modifier = Modifier.background(color = Color.White),
                    call = call,
                    onBackPressed = { onBackPressed() },
                    controlsContent = {
                        ControlActions(
                            call = call,
                            actions = listOf(
                                {
                                    ToggleCameraAction(
                                        modifier = Modifier.size(52.dp),
                                        isCameraEnabled = isCameraEnabled,
                                        onCallAction = { call.camera.setEnabled(it.isEnabled) }
                                    )
                                },
                                {
                                    ToggleMicrophoneAction(
                                        modifier = Modifier.size(52.dp),
                                        isMicrophoneEnabled = isMicrophoneEnabled,
                                        onCallAction = { call.microphone.setEnabled(it.isEnabled) }
                                    )
                                },
                                {
                                    FlipCameraAction(
                                        modifier = Modifier.size(52.dp),
                                        onCallAction = { call.camera.flip() }
                                    )
                                },
                                {
                                    IconButton(onClick = {
                                        lifecycleScope.launch {
                                            if (isRecording) {
                                                call.stopRecording().onError {
                                                    Toast.makeText(
                                                        applicationContext,
                                                        it.message,
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            } else {
                                                call.startRecording().onError {
                                                    Toast.makeText(
                                                        applicationContext,
                                                        it.message,
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        }
                                    }) {
                                        Icon(
                                            painter = painterResource(id = if (isRecording) R.drawable.ic_stop else R.drawable.baseline_fiber_manual_record_24),
                                            contentDescription = if (isRecording) "Stop Recording" else "Start Recording",
                                            tint = if (isRecording) Color.Red else Color.Black
                                        )
                                    }
                                }, {
                                    Button(onClick = {
                                        lifecycleScope.launch {
                                            call.listRecordings()
                                                .onSuccess { response ->
                                                    recordingsList.clear()
                                                    recordingsList.addAll(response.recordings)
                                                    showRecordings = true
                                                }
                                                .onError { error ->
                                                    Toast.makeText(
                                                        applicationContext,
                                                        "Error: ${error.message}",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                        }


                                    }) {
                                        Text("Fetch Recordings")
                                    }



                                }
                            )
                        )
                    }
                )
            }
            if (showRecordings) {
                val context = LocalContext.current
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                            .background(Color.White)

                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            itemsIndexed(recordingsList) { index, recording ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = recording.filename,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = recording.startTime.toString(),
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.End
                                    )
                                    Text(
                                        text = recording.endTime.toString(),
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.End
                                    )
                                    ClickableText(
                                        text = buildAnnotatedString {
                                            append(recording.filename)
                                        },
                                        onClick = { offset ->
                                            // عند الضغط على النص، قم بفتح الرابط أو ما ترغب به
                                            val url = recording.url
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                            try {
                                                context.startActivity(intent)
                                            } catch (e: Exception) {
                                                Toast.makeText(context, "No application to handle this action", Toast.LENGTH_SHORT).show()
                                            }
                                        },
                                        modifier = Modifier.weight(1f)
                                    )
//                                    IconButton(onClick = {
//                                        lifecycleScope.launch {
//                                            call.deleteRecording(recordingId = recording.id) // Assuming CallRecording has an id property
//                                                .onSuccess {
//                                                    // Remove the recording from the list
//                                                    recordingsList.remove(recording)
//                                                }
//                                                .onError { error ->
//                                                    Toast.makeText(context, "Error deleting recording: ${error.message}", Toast.LENGTH_SHORT).show()
//                                                }
//                                        }
//                                    }) {
//                                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Recording")
//                                    }

                                }
                            }
                        }
                    }
                }
            }


        }
    }
}
