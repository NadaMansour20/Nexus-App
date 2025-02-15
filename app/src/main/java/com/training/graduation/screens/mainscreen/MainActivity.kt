package com.training.graduation.screens.mainscreen

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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.training.graduation.microsoft.MSALAuthScreen
//import com.training.graduation.microsoft.MSALAuthScreen

import com.training.graduation.onboarding.OnboardingScreen
import com.training.graduation.screens.Authentication.AuthViewModel
import com.training.graduation.screens.chat.ChatListScreen
import com.training.graduation.screens.Authentication.ForgotPasswordScreen
import com.training.graduation.screens.group.GroupListScreen
import com.training.graduation.screens.Authentication.LoginScreen
import com.training.graduation.screens.schedule.ScheduleMeeting
import com.training.graduation.screens.Authentication.SignupScreen
import com.training.graduation.screens.notification.NotificationScreen
import com.training.graduation.screens.profile.Profile
import com.training.graduation.screens.profile.UserProfileScreen
import com.training.graduation.screens.sharedprefrence.PreferenceManager
import com.training.graduation.screens.sharedprefrence.UpdateLocale
import com.training.graduation.screens.startmeeting.JitsiMeetCompose
import com.training.graduation.ui.theme.GraduationTheme

//import com.training.graduation.screens.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val authViewModel : AuthViewModel by viewModels()






//
//        runBlocking {
//            val database: MongoDatabase = DatabaseModule.provideMongoDatabase()
//
//            val collections = database.listCollectionNames().toList()
//
//            Log.e("DatabaseCollections", "Collections in the database: $collections")
//
//        }

//        val client =MongoClient.create(connectionstring="mongodb+srv://nexus:nexusMongoDb@cluster0.nc4w0.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")




//
//
//        val serverURL = URL("https://meet.jit.si")
//        val defaultOptions = JitsiMeetConferenceOptions.Builder()
//            .setServerURL(serverURL)
//            .setFeatureFlag("welcomepage.enabled", false)
//            .build()
//        JitsiMeet.setDefaultConferenceOptions(defaultOptions)
//
//

        val preferenceManager = PreferenceManager(this)

        // Apply saved language on app launch
        UpdateLocale(this, preferenceManager.getLanguage())

        setContent {
            GraduationTheme {
                Scaffold { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding),preferenceManager,authViewModel)
                    MSALAuthScreen(context = LocalContext.current)

                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier,preferenceManager:PreferenceManager,authViewModel:AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Onboarding") {
        composable(route = "Onboarding") {
            OnboardingScreen (modifier, navController, innerpadding = PaddingValues())
        }
        composable(route = "loginscreen") {
            LoginScreen(modifier, navController,authViewModel ,  PaddingValues())
        }
        composable(route = "signupscreen") {
            SignupScreen(modifier, navController,authViewModel, innerpadding = PaddingValues())
        }
        composable(route = "homescreen") {
            HomeScreen(modifier, navController,authViewModel, innerpadding = PaddingValues())
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
//        composable(route = "start_meeting") {
//            StartMeeting()
//        }
        composable(route="editProfile"){
            Profile(navController)
        }
        composable(route="notification_screen"){
            NotificationScreen( navController)
        }
        composable(route="start_meeting") {
          JitsiMeetCompose(navController)
        }

    }




}




