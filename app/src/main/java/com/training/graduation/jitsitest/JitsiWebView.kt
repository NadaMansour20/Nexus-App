//import android.annotation.SuppressLint
//import android.os.Handler
//import android.os.Looper
//import android.webkit.WebChromeClient
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.compose.ui.unit.dp
//import org.json.JSONObject
//
//@SuppressLint("SetJavaScriptEnabled")
//@Composable
//fun JitsiMeetingScreen(meetingRoom: String) {
//    val context = LocalContext.current
//    val participants = remember { mutableStateListOf<Participant>() }
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        // WebView
//        AndroidView(
//            factory = {
//                WebView(context).apply {
//                    settings.javaScriptEnabled = true
//                    settings.mediaPlaybackRequiresUserGesture = false
//                    settings.domStorageEnabled = true
//                    settings.allowFileAccess = true
//                    settings.allowContentAccess = true
//
//                    webChromeClient = WebChromeClient()
//                    webViewClient = WebViewClient()
//
//                    // Bridge to Android
//                    addJavascriptInterface(JitsiInterface(participants), "Android")
//
//                    // HTML content with Jitsi iframe
//                    val htmlContent = """
//                        <!DOCTYPE html>
//                        <html>
//                        <head>
//                            <script src="https://meet.jit.si/external_api.js"></script>
//                        </head>
//                        <body style="margin:0;padding:0;overflow:hidden;">
//                            <div id="jitsi-container" style="width:100%; height:100%;"></div>
//                            <script>
//                                const domain = "meet.jit.si";
//                                const options = {
//                                    roomName: "$meetingRoom",
//                                    parentNode: document.getElementById('jitsi-container'),
//                                    userInfo: {
//                                        displayName: "YourName"
//                                    }
//                                };
//                                const api = new JitsiMeetExternalAPI(domain, options);
//
//                                api.addListener('participantJoined', (event) => {
//                                    Android.onParticipantJoined(JSON.stringify(event));
//                                });
//
//                                api.addListener('participantLeft', (event) => {
//                                    Android.onParticipantLeft(JSON.stringify(event));
//                                });
//                            </script>
//                        </body>
//                        </html>
//                    """.trimIndent()
//
//                    loadDataWithBaseURL(
//                        "https://meet.jit.si",
//                        htmlContent,
//                        "text/html",
//                        "UTF-8",
//                        null
//                    )
//                }
//            },
//            modifier = Modifier.fillMaxSize()  // تغيير من .weight(1f) إلى .fillMaxSize()
//        )
//
//        // قائمة المشاركين
//        ParticipantList(participants)
//    }
//}
//
//// موديل الداتا بتاع المشاركين
//data class Participant(
//    val id: String,
//    val name: String
//)
//
//// عرض المشاركين
//@Composable
//fun ParticipantList(participants: List<Participant>) {
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        items(participants) { participant ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp),
//            ) {
//                Column(modifier = Modifier.padding(8.dp)) {
//                    Text(text = "Name: ${participant.name}")
//                    Text(text = "ID: ${participant.id}")
//                }
//            }
//        }
//    }
//}
