package com.training.graduation.screens.startmeeting

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.training.graduation.R
import com.training.graduation.following.fetchWebhookEvents
import com.training.graduation.following.trackUserEvent
import com.training.graduation.token.RetrofitBuild
import com.training.graduation.token.TokenRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetUserInfo
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JitsiMeetCompose(navController: NavController) {

    var roomName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var roomNameError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.let_s_start_your_meeting)
                )
            })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = roomName,
                onValueChange = {
                    roomName = it
                    roomNameError = it.isBlank()
                },
                label = { Text(stringResource(R.string.meeting_name)) },
                isError = roomNameError,
                supportingText = {
                    if (roomNameError) {
                        Text(
                            stringResource(R.string.please_enter_meeting_name),
                            color = Color.Red
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it.isBlank()
                },
                label = { Text(stringResource(R.string.password)) },
                isError = passwordError,
                supportingText = {
                    if (passwordError) {
                        Text(
                            stringResource(R.string.please_enter_password),
                            color = Color.Red
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // زر Join Meeting
            Button(
                onClick = {
                    if (roomName.isNotBlank() && password.isNotBlank()) {
                        roomNameError = false
                        passwordError = false
                        startJitsiMeeting(context, roomName, password)
                    } else {
                        roomNameError = roomName.isBlank()
                        passwordError = password.isBlank()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF000000), Color(0xFF3533CD)),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        ),
                        shape = RoundedCornerShape(30.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.join_meeting))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // زر Send Invitation
            Button(
                onClick = {
                    if (roomName.isNotBlank() && password.isNotBlank()) {
                        sendInvitation(context, roomName, password)
                    } else {
                        roomNameError = roomName.isBlank()
                        passwordError = password.isBlank()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF000000), Color(0xFF3533CD)),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        ),
                        shape = RoundedCornerShape(30.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.send_invitation))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ✅ زر Fetch Webhook Events
            Button(
                onClick = {
                    fetchWebhookEvents()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text("🔍 Show Webhook Events")
            }
        }
    }
}


fun startJitsiMeeting(context: Context, roomName: String, password: String){
    if (roomName.isBlank() || password.isBlank()) return

    val scope = CoroutineScope(Dispatchers.Main)
    val userName = "Nada" // تقدرِ تخليه dynamic بعدين

    scope.launch {
        val request = TokenRequest(
            name = userName,
            email = "$userName@email.com",
            room ="vpaas-magic-cookie-5539cb854a4d47aba650f080c97d11b9/01f783/$roomName",
            isModerator = true
        )

        try {
            val response = RetrofitBuild.get_api().getToken(request)

            if (response.isSuccessful) {
                val token = response.body()?.token ?: return@launch
                val serverURL = URL("https://jaas.8x8.vc")

                val options = JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .setRoom(roomName)
                    .setToken(token)
                    .setAudioMuted(true)
                    .setVideoMuted(true)
                    .setUserInfo(
                        JitsiMeetUserInfo().apply {
                            displayName = userName
                            email = "$userName@email.com"
                        }
                    )
                    .setFeatureFlag("welcomepage.enabled", true)
                    .setFeatureFlag("add-people.enabled", true)
                    .setFeatureFlag("conference-timer.enabled", true)
                    .setFeatureFlag("require-display-name", false)
                    .setFeatureFlag("start-without-moderator", true)
                    .setFeatureFlag("security-options.enabled", true)
                    .setFeatureFlag("joinBeforeHost", true)
                    .setFeatureFlag("chat.enabled", true)
                    .setFeatureFlag("close-captions.enabled", true)
                    .setFeatureFlag("invite.enabled", true)
                    .setFeatureFlag("live-streaming.enabled", true)
                    .setFeatureFlag("meeting-name.enabled", true)
                    .setFeatureFlag("meeting-password.enabled", true)
                    .setFeatureFlag("pip.enabled", true)
                    .setFeatureFlag("raise-hand.enabled", true)
                    .setFeatureFlag("recording.enabled", true)
                    .setFeatureFlag("tile-view.enabled", true)
                    .setFeatureFlag("video-share.enabled", true)
                    .setFeatureFlag("screen-sharing.enabled", true)
                    .setFeatureFlag("resolution", 720)
                    .setFeatureFlag("lobby-mode.enabled", false)
                    .setFeatureFlag("notifications.enabled", true)
                    .setFeatureFlag("prejoinpage.enabled", true)
                    .setFeatureFlag("filmstrip.enabled", true)
                    .setFeatureFlag("overflow-menu.enabled", true)
                    .setFeatureFlag("server-url-change.enabled", false)
                    .setFeatureFlag("speakerstats.ordering.enabled", true)
                    .setFeatureFlag("audio-mute.enabled", true)
                    .setFeatureFlag("video-mute.enabled", true)
                    .setFeatureFlag("audio-only.enabled", false)
                    .setFeatureFlag("calendar.enabled", false)
                    .setFeatureFlag("call-integration.enabled", false)
                    .setFeatureFlag("live-streaming.url", "https://youtube.com/live/stream_key_here")
                    .build()

                JitsiMeetActivity.launch(context, options)
                trackUserEvent()
            } else {
                Toast.makeText(context, "Failed to get token", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}

fun sendInvitation(context: Context, roomName: String, password: String) {
    if (roomName.isBlank() || password.isBlank()) return

    val meetingLink = "https://meet.jit.si/$roomName?password=$password"
    val invitationMessage = context.getString(
        R.string.you_are_invited_to_join_the_meeting_click_the_link_below,
        meetingLink
    )

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, invitationMessage)
    }

    context.startActivity(Intent.createChooser(intent, "Send Invitation via"))
}

