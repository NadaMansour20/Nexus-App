package com.training.graduation

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
//import com.squareup.wire.internal.Serializable
import com.training.graduation.onboarding.OnboardingScreen
import com.training.graduation.screens.ChatListScreen
import com.training.graduation.screens.ForgotPasswordScreen
import com.training.graduation.screens.GroupListScreen
import com.training.graduation.screens.HomeScreen
import com.training.graduation.screens.LoginScreen
import com.training.graduation.screens.schedule.ScheduleMeeting
import com.training.graduation.screens.SignupScreen
import com.training.graduation.screens.SplashScreen
import com.training.graduation.screens.StartMeeting
import com.training.graduation.screens.profile.Profile
import com.training.graduation.screens.profile.UserProfileScreen
import com.training.graduation.screens.sharedprefrence.PreferenceManager
import com.training.graduation.screens.sharedprefrence.UpdateLocale
import com.training.graduation.ui.theme.GraduationTheme
//import com.training.graduation.screens.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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
            ChatListScreen(modifier, navController, innerpadding = PaddingValues())
        }
        composable(route = "group") {
            GroupListScreen(navController)
        }
        composable(route = "schedule") {
            ScheduleMeeting()
        }
        composable(route = "start_meeting") {
            StartMeeting()
        }
        composable(route="editProfile"){
            Profile()
        }

    }




}


