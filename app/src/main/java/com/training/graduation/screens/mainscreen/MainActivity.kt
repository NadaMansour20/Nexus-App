package com.training.graduation.screens.mainscreen

import SignupScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.onesignal.OneSignal
import com.training.graduation.onboarding.OnboardingScreen
import com.training.graduation.screens.Authentication.AuthViewModel
import com.training.graduation.screens.chat.ChatListScreen
import com.training.graduation.screens.Authentication.ForgotPasswordScreen
import com.training.graduation.screens.group.GroupListScreen
import com.training.graduation.screens.Authentication.LoginScreen
import com.training.graduation.screens.schedule.ScheduleMeeting
import com.training.graduation.screens.notification.NotificationScreen
import com.training.graduation.screens.profile.Profile
import com.training.graduation.screens.profile.UserProfileScreen
import com.training.graduation.screens.sharedprefrence.PreferenceManager
import com.training.graduation.screens.sharedprefrence.UpdateLocale
import com.training.graduation.screens.startmeeting.JitsiMeetCompose
import com.training.graduation.token.Constent
import com.training.graduation.ui.theme.GraduationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val deviceState = OneSignal.User.pushSubscription
        Log.e("OneSignalCheck", "Player ID = ${deviceState?.id}")


        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)


//            val playerId = OneSignal.User.pushSubscription.id
//            Log.e("Player ID", "PlayerId is: $playerId")
        }



        val authViewModel: AuthViewModel by viewModels()

        val preferenceManager = PreferenceManager(this)

        // Apply saved language on app launch
        UpdateLocale(this, preferenceManager.getLanguage())

        JitsiMeetActivityDelegate.onHostResume(this)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("FCM_TOKEN", token)
                // ممكن تبعته لسيرفرك هنا
            } else {
                Log.e("FCM_TOKEN", "Token fetch failed", task.exception)
            }
        }

        setContent {
            GraduationTheme {
                Scaffold { innerPadding ->
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        preferenceManager,
                        authViewModel
                    )
                }
            }
        }
    }

    @Composable
    fun AppNavigation(
        modifier: Modifier,
        preferenceManager: PreferenceManager,
        authViewModel: AuthViewModel
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "Onboarding") {
            composable(route = "Onboarding") {
                OnboardingScreen(modifier, navController, innerpadding = PaddingValues())
            }
            composable(route = "loginscreen") {
                LoginScreen(modifier, navController, authViewModel, PaddingValues())
            }
            composable(route = "signupscreen") {
                SignupScreen(modifier, navController, authViewModel, innerpadding = PaddingValues())
            }
            composable(route = "homescreen") {
                HomeScreen(modifier, navController, authViewModel, innerpadding = PaddingValues())
            }
            composable(route = "forgotpassword") {
                ForgotPasswordScreen(modifier, navController, innerpadding = PaddingValues())

            }
            composable(route = "userprofile") {
                UserProfileScreen(navController, preferenceManager = preferenceManager)
            }
            composable(route = "chat") {
                ChatListScreen(navController)
            }
            composable(route = "group") {
                GroupListScreen(navController)
            }
            composable(route = "schedule") {
                ScheduleMeeting(navController)
            }
//        composable(route = "start_meeting") {
//            StartMeeting()
//        }
            composable(route = "editProfile") {
                Profile(navController)
            }
            composable(route = "notification_screen") {
                NotificationScreen(navController)
            }
            composable(route = "start_meeting") {
                JitsiMeetCompose(navController)
            }

        }

    }
}




