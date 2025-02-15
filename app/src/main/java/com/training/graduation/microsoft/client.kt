package com.training.graduation.microsoft

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import com.training.graduation.R

@Composable
fun MSALAuthScreen(context: Context) {
    var msalClient by remember { mutableStateOf<IMultipleAccountPublicClientApplication?>(null) }
    var accessToken by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val currentContext by rememberUpdatedState(context) // لتحديث القيمة عند إعادة التركيب

    LaunchedEffect(Unit) {
        PublicClientApplication.createMultipleAccountPublicClientApplication(
            currentContext.applicationContext,
            R.raw.msal_config,
            object : IPublicClientApplication.IMultipleAccountApplicationCreatedListener {
                override fun onCreated(application: IMultipleAccountPublicClientApplication?) {
                    msalClient = application
                    Log.e("MSAL", "✅ MSAL Client Created Successfully!")
                }

                override fun onError(exception: MsalException) {
                    errorMessage = "Error creating MSAL Client: ${exception.message}"
                    Log.e("MSAL", "❌ Error Creating MSAL Client: ${exception.message}")
                }
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (msalClient == null) {
            CircularProgressIndicator()
        } else {
            Button(onClick = {
                val activity = currentContext as? Activity
                if (activity != null) {
                    msalClient?.acquireToken(activity, arrayOf("User.Read"), object : AuthenticationCallback {
                        override fun onSuccess(authenticationResult: IAuthenticationResult?) {
                            authenticationResult?.let {
                                accessToken = it.accessToken
                                Log.e("MSAL", "✅ Login Successful! Token: $accessToken")
                            }
                        }

                        override fun onError(exception: MsalException?) {
                            errorMessage = "Authentication error: ${exception?.message}"
                            Log.e("MSAL", "❌ Authentication Error: ${exception?.message}")
                        }

                        override fun onCancel() {
                            errorMessage = "User canceled login"
                            Log.e("MSAL", "❌ User Canceled Login")
                        }
                    })
                } else {
                    errorMessage = "Context is not an Activity"
                    Log.e("MSAL", "❌ Context is not an Activity!")
                }
            }) {
                Text(text = "Login with Microsoft")
            }

            accessToken?.let {
                Text(text = "Access Token: $it", fontSize = 14.sp, color = Color.Green)
            }

            errorMessage?.let {
                Text(text = "Error: $it", fontSize = 14.sp, color = Color.Red)
            }
        }
    }
}
