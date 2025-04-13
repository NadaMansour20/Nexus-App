package com.training.graduation.following

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class EventPayload(
    val event: String,
    val userId: String,
    val name: String,
    val room: String
)

data class EventLog(
    val time: String,
    val event: String,
    val data: Map<String, Any>
)

interface WebhookApi {
    @POST("/webhook")
    fun sendEvent(@Body payload: EventPayload): Call<Void>

    @GET("/events")
    fun getEvents(): Call<List<EventLog>>
}

fun trackUserEvent() {
    val payload = EventPayload(
        event = "mobile-user-joined",
        userId = "user123",
        name = "Nada",
        room = "test-room"
    )

    RetrofitBuild.get_webhook_api().sendEvent(payload).enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            Log.d("Webhook", "✅ Event sent successfully")
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Log.e("Webhook", "❌ Failed to send event: ${t.message}")
        }
    })
}

fun fetchWebhookEvents() {
    RetrofitBuild.get_webhook_api().getEvents().enqueue(object : Callback<List<EventLog>> {
        override fun onResponse(call: Call<List<EventLog>>, response: Response<List<EventLog>>) {
            val logs = response.body() ?: return
            for (log in logs) {
                Log.d("JITSI_WEBHOOK", "🕒 ${log.time} - 📣 ${log.event}")
                Log.d("JITSI_WEBHOOK", "📦 Data: ${log.data}")
            }
        }

        override fun onFailure(call: Call<List<EventLog>>, t: Throwable) {
            Log.e("JITSI_WEBHOOK", "❌ Failed to fetch events: ${t.message}")
        }
    })
}
