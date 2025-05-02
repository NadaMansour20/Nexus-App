import android.util.Log
import com.onesignal.OneSignal
import com.training.graduation.token.Constent
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

fun scheduleNotification(
    meetingTimeMillis: Long,
    meetingTitle: String,
    meetingDescription: String,
    meetingLink: String
)
{
    val oneHourBefore = meetingTimeMillis - 3600 * 1000 // قبل ساعة
    val sendTime = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'GMT+0200'")
        .apply { timeZone = java.util.TimeZone.getTimeZone("GMT+2") }
        .format(java.util.Date(oneHourBefore))

    val deviceState = OneSignal.User.pushSubscription
    val playerId = deviceState.id
    Log.e("OneSignalCheck", "Player ID = $playerId")


    val client = OkHttpClient()

    val json = JSONObject().apply {
        put("app_id", Constent.ON_SIGNAL_APP_ID)
        put("included_segments", arrayOf("All"))
        put("headings", JSONObject().put("en", "Meeting Reminder $meetingTitle"))
        put("contents", JSONObject().put("en", "You have a meeting in 2 hour! about $meetingDescription"))
//        put("send_after", sendTime)
        put("url", meetingLink)
    }

    val body = RequestBody.create(
        "application/json; charset=utf-8".toMediaTypeOrNull(),
        json.toString()
    )

    val request = Request.Builder()
        .url("https://onesignal.com/api/v1/notifications")
        .post(body)
        .addHeader("Authorization", "Basic ${Constent.ON_SIGNAL_API_KEY}")
        .addHeader("Content-Type", "application/json")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("❌ Failed to send notification","hhhhhhhhhhhhh")
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body?.string()
            Log.e("✅ Response code, body:","$responseBody")
            Log.e("✅ Response body: " ,"$responseBody")
        }


    })
}
