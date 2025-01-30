package com.android.meetingjitis

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JitsiMeetCompose() {
    var roomName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") } // حالة جديدة لكلمة المرور
    val context = LocalContext.current // الحصول على السياق من Compose

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Jitsi Meet Integration") })
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
            // حقل إدخال اسم الغرفة
            OutlinedTextField(
                value = roomName,
                onValueChange = { roomName = it },
                label = { Text("Room Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // حقل إدخال كلمة المرور
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // زر بدء الاجتماع
            Button(
                onClick = { startJitsiMeeting(context, roomName, password) }, // تمرير السياق واسم الغرفة وكلمة المرور
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Join Meeting")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // زر إرسال الدعوة
            Button(
                onClick = { sendInvitation(context, roomName, password) }, // إرسال الدعوة
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send Invitation")
            }
        }
    }
}

fun startJitsiMeeting(context: Context, roomName: String, password: String) {
    if (roomName.isBlank()) return // التأكد من أن اسم الغرفة ليس فارغًا

    try {
        val serverURL = URL("https://meet.jit.si")

        // إنشاء رابط الاجتماع مع كلمة المرور
        val roomURL = if (password.isNotBlank()) {
            "$roomName?password=$password"
        } else {
            roomName
        }
        val options = JitsiMeetConferenceOptions.Builder()
            .setServerURL(serverURL)
            .setRoom(roomURL)
            .setAudioMuted(false) // إعداد الصوت
            .setVideoMuted(false) // إعداد الفيديو
            .setFeatureFlag("require-display-name", false) // تعطيل شرط اسم العرض
            .setFeatureFlag("start-without-moderator", true) // بدء الاجتماع دون مشرف
            .setFeatureFlag("welcomepage.enabled", false) // تعطيل صفحة الترحيب
            .build()

        // إطلاق النشاط باستخدام السياق الصحيح
        JitsiMeetActivity.launch(context, options)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun sendInvitation(context: Context, roomName: String, password: String) {
    if (roomName.isBlank() || password.isBlank()) return

    val meetingLink = "https://meet.jit.si/$roomName?password=$password" // إنشاء رابط الاجتماع مع كلمة المرور
    val invitationMessage = "You are invited to join the meeting. Click the link below:\n$meetingLink" // رسالة الدعوة

    // إنشاء نية (Intent) لإرسال الرسالة عبر تطبيق البريد الإلكتروني أو الرسائل
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, invitationMessage)
    }

    // بدء نشاط إرسال الرسالة
    context.startActivity(Intent.createChooser(intent, "Send Invitation via"))
}


/*@Composable
fun JitsiMeetingScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { navController.popBackStack() }) {
            Text("رجوع")
        }
        JitsiWebView()
//        AndroidView(
//            modifier = Modifier.fillMaxSize(),
//            factory = { context ->
//                WebView(context).apply {
//                    settings.apply {
//                        javaScriptEnabled = true
//                        domStorageEnabled = true
//                        mediaPlaybackRequiresUserGesture = false
//                        allowFileAccess = true
//                        allowContentAccess = true
//                        allowFileAccessFromFileURLs = true
//                        allowUniversalAccessFromFileURLs = true
//                    }
//                    webChromeClient = WebChromeClient()
//                    webViewClient = object : WebViewClient() {
//                        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                            return false
//                        }
//                    }
//                    loadUrl("file:///android_asset/jitsi.html")
//                }
//            }
//        )
    }
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun JitsiWebView() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    mediaPlaybackRequiresUserGesture = false
                    allowFileAccess = true
                    allowContentAccess = true
                    allowFileAccessFromFileURLs = true  // ✅ حل المشكلة
                    allowUniversalAccessFromFileURLs = true // ✅ حل المشكلة
                }
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        return false
                    }
                }
                loadUrl("file:///android_asset/jitsi.html")
            }
        }
    )
}*/