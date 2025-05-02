import android.webkit.JavascriptInterface
import org.json.JSONObject
import androidx.compose.runtime.snapshots.SnapshotStateList

class JitsiInterface(private val participants: SnapshotStateList<Participant>) {

    @JavascriptInterface
    fun onParticipantJoined(data: String) {
        val obj = JSONObject(data)
        val id = obj.optString("id", "")
        val name = obj.optString("displayName", "Unknown")

        participants.add(Participant(id, name))
    }

    @JavascriptInterface
    fun onParticipantLeft(data: String) {
        val obj = JSONObject(data)
        val id = obj.optString("id", "")

        participants.removeAll { it.id == id }
    }
}
