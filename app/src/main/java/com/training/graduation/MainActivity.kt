package com.training.graduation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.squareup.wire.internal.Serializable
import com.training.graduation.navigation.BottomNavigationBar
import com.training.graduation.screens.ChatListScreen
import com.training.graduation.screens.ForgotPasswordScreen
import com.training.graduation.screens.GroupListScreen
import com.training.graduation.screens.HomeScreen
import com.training.graduation.screens.LoginScreen
import com.training.graduation.screens.Profile
import com.training.graduation.screens.ScheduleMeeting
import com.training.graduation.screens.SignupScreen
import com.training.graduation.screens.SplashScreen
import com.training.graduation.screens.StartMeeting
import com.training.graduation.screens.StartScreen
import com.training.graduation.screens.UserProfileScreen
import com.training.graduation.ui.theme.GraduationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraduationTheme {
                Scaffold { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splashscreen") {
        composable(route = "splashscreen") {
            SplashScreen(modifier, navController, innerpadding = PaddingValues())
        }
        composable(route = "startscreen") {
            StartScreen(modifier, navController, innerpadding = PaddingValues())
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
            UserProfileScreen(navController)
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


