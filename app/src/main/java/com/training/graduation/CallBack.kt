//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.util.Base64
//
//class CallbackActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // استقبال الـ intent اللي جه من Zoom
//        val uri: Uri? = intent.data
//        if (uri != null) {
//            val code = uri.getQueryParameter("code") // تجيب الـ Authorization Code
//            if (code != null) {
//                // استخدمي الـ code علشان تجيبي Access Token
//                getAccessToken(code)
//                Log.e("Codeeeeeeee", "Codeeee+ $code")
//            } else {
//                Log.e("Error", "No code received")
//                // لو مفيش code، توجّهي المستخدم لصفحة Zoom تاني
//                redirectToZoomAuth()
//            }
//        } else {
//            Log.e("Error", "No data received")
//            // لو مفيش data، توجّهي المستخدم لصفحة Zoom تاني
//            redirectToZoomAuth()
//        }
//        finish() // إغلاق الـ Activity بعد ما تستقبلي الـ code
//    }
//
//    private fun getAccessToken(code: String) {
//        val clientId = "LhAg7qEjQLSUdzb7URamNA"
//        val clientSecret = "F6lfp6Oa44cV2An7OPkx20ky7B2p05vo"
//        val redirectUri = "nexus://callback"
//
//        // إنشاء الـ Authorization Header
//        val authHeader = "Basic " + Base64.getEncoder().encodeToString("$clientId:$clientSecret".toByteArray())
//
//        // عمل الـ request باستخدام Retrofit
//        RetrofitClient.instance.getAccessToken(authHeader, "authorization_code", code, redirectUri)
//            .enqueue(object : Callback<AccessTokenResponse> {
//                override fun onResponse(call: Call<AccessTokenResponse>, response: Response<AccessTokenResponse>) {
//                    if (response.isSuccessful) {
//                        val accessToken = response.body()?.access_token
//                        Log.d("AccessToken", accessToken ?: "No access token")
//                        // هنا تقدر تحفظي الـ Access Token علشان تستخدميه في الطلبات التانية
//                    } else {
//                        Log.e("Error", "Request failed: ${response.code()}")
//                        // لو الـ request فشل، توجّهي المستخدم لصفحة Zoom تاني
//                        redirectToZoomAuth()
//                    }
//                }
//
//                override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
//                    Log.e("Error", t.message ?: "Unknown error")
//                    // لو الـ request فشل، توجّهي المستخدم لصفحة Zoom تاني
//                    redirectToZoomAuth()
//                }
//            })
//    }
//
//    private fun redirectToZoomAuth() {
//        val clientId = "LhAg7qEjQLSUdzb7URamNA"
//        val redirectUri = "nexus://callback"
//
//        // إنشاء الرابط علشان توجّهي المستخدم لصفحة Zoom
//        val authUrl = "https://zoom.us/oauth/authorize?response_type=code&client_id=$clientId&redirect_uri=$redirectUri"
//
//        // فتح الرابط في متصفح أو WebView
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
//        startActivity(intent)
//    }
//
//    private fun refreshAccessToken(refreshToken: String) {
//        val clientId = "LhAg7qEjQLSUdzb7URamNA"
//        val clientSecret = "F6lfp6Oa44cV2An7OPkx20ky7B2p05vo"
//
//        // إنشاء الـ Authorization Header
//        val authHeader = "Basic " + Base64.getEncoder().encodeToString("$clientId:$clientSecret".toByteArray())
//
//        // عمل الـ request باستخدام Retrofit
//        RetrofitClient.instance.refreshAccessToken(authHeader, "refresh_token", refreshToken)
//            .enqueue(object : Callback<AccessTokenResponse> {
//                override fun onResponse(call: Call<AccessTokenResponse>, response: Response<AccessTokenResponse>) {
//                    if (response.isSuccessful) {
//                        val accessToken = response.body()?.access_token
//                        Log.d("AccessToken", accessToken ?: "No access token")
//                        // هنا تقدر تحفظي الـ Access Token الجديد علشان تستخدميه في الطلبات التانية
//                    } else {
//                        Log.e("Error", "Request failed: ${response.code()}")
//                        // لو الـ request فشل، توجّهي المستخدم لصفحة Zoom تاني
//                        redirectToZoomAuth()
//                    }
//                }
//
//                override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
//                    Log.e("Error", t.message ?: "Unknown error")
//                    // لو الـ request فشل، توجّهي المستخدم لصفحة Zoom تاني
//                    redirectToZoomAuth()
//                }
//            })
//    }
//}