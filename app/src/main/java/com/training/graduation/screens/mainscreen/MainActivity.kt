package com.training.graduation.screens.mainscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.training.graduation.onboarding.OnboardingScreen
import com.training.graduation.screens.chat.ChatListScreen
import com.training.graduation.screens.Authentication.ForgotPasswordScreen
import com.training.graduation.screens.group.GroupListScreen
import com.training.graduation.screens.Authentication.LoginScreen
import com.training.graduation.screens.schedule.ScheduleMeeting
import com.training.graduation.screens.Authentication.SignupScreen
import com.training.graduation.screens.Authentication.SplashScreen
import com.training.graduation.screens.notification.NotificationScreen
import com.training.graduation.screens.startmeeting.StartMeeting
import com.training.graduation.screens.profile.Profile
import com.training.graduation.screens.profile.UserProfileScreen
import com.training.graduation.screens.sharedprefrence.PreferenceManager
import com.training.graduation.screens.sharedprefrence.UpdateLocale
import com.training.graduation.screens.startmeeting.JitsiMeetCompose
import com.training.graduation.ui.theme.GraduationTheme
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.URL

//import com.training.graduation.screens.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        // إعداد الخيارات الافتراضية لـ Jitsi Meet
        val serverURL = URL("https://meet.jit.si")
        val defaultOptions = JitsiMeetConferenceOptions.Builder()
            .setServerURL(serverURL)
            // تعيين الإعدادات الافتراضية هنا
            .setFeatureFlag("welcomepage.enabled", false)
            .build()
        JitsiMeet.setDefaultConferenceOptions(defaultOptions)


//        val clientId = "LhAg7qEjQLSUdzb7URamNA"
//        val redirectUri = "nexus://callback"
//
//        val authUrl = "https://zoom.us/oauth/authorize?response_type=code&client_id=$clientId&redirect_uri=$redirectUri"
//
//        // فتح الرابط في متصفح أو WebView
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
//        startActivity(intent)
//
//
//
        val preferenceManager = PreferenceManager(this)

        // Apply saved language on app launch
        UpdateLocale(this, preferenceManager.getLanguage())

        setContent {
            GraduationTheme {
                Scaffold { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding),preferenceManager)

                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier,preferenceManager:PreferenceManager) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Onboarding") {
        composable(route = "splashscreen") {
            SplashScreen(modifier, navController, innerpadding = PaddingValues())
        }
        composable(route = "Onboarding") {
            OnboardingScreen (modifier, navController, innerpadding = PaddingValues())
        }
        composable(route = "loginscreen") {
            LoginScreen(modifier, navController, innerpadding = PaddingValues())
        }
        composable(route = "signupscreen") {
            SignupScreen(modifier, navController, innerpadding = PaddingValues())
        }
        composable(route = "homescreen") {
            HomeScreen(modifier, navController, innerpadding = PaddingValues())
        }
        composable(route = "forgotpassword") {
            ForgotPasswordScreen(modifier, navController, innerpadding = PaddingValues())

        }
        composable(route = "userprofile") {
            UserProfileScreen(navController,preferenceManager =preferenceManager)
        }
        composable(route = "chat") {
            ChatListScreen( navController)
        }
        composable(route = "group") {
            GroupListScreen(navController)
        }
        composable(route = "schedule") {
            ScheduleMeeting(navController)
        }
        composable(route = "start_meeting") {
            StartMeeting()
        }
        composable(route="editProfile"){
            Profile(navController)
        }
        composable(route="notification_screen"){
            NotificationScreen( navController)
        }
        composable(route="start_meeting") {
          JitsiMeetCompose()
        }

    }




}


