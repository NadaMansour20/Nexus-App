package com.training.graduation.screens

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.training.graduation.R
import com.training.graduation.navigation.BottomNavigationBar
import java.util.Locale

@Composable
fun UserProfileScreen(navController: NavController){
    BottomNavigationBar(navController = navController)

    val layoutDirection = LocalLayoutDirection.current

    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
            ,
            verticalAlignment = Alignment.CenterVertically

        ) {
            ProfileImage1()

            // name from database
            Text(
                "Nada Mansour Samir",
                modifier = Modifier.padding(25.dp),
                fontSize = 17.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        EditProfile(Modifier.padding( horizontal = 20.dp),navController)

        AddAccount(Modifier.padding(vertical = 30.dp, horizontal = 20.dp),layoutDirection)

        ContactInfo(Modifier.padding(vertical = 25.dp, horizontal = 20.dp))

        Info(Modifier.padding(horizontal = 40.dp))

        Setting(Modifier.padding(vertical = 40.dp, horizontal = 20.dp),layoutDirection)

        Notification(Modifier.padding(vertical = 10.dp, horizontal = 20.dp))

        Privacy(Modifier.padding(vertical = 40.dp, horizontal = 20.dp))

        SignOut(Modifier.padding( horizontal = 20.dp),layoutDirection)

    }





}



@Composable
fun ProfileImage1() {
    val image = painterResource(id = R.drawable.image11)

    Image(
        painter = image,
        contentDescription = "Circular Image",
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}

@Composable
fun EditProfile(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp)

            )
            .clip(RoundedCornerShape(10.dp))
            .background(color = colorResource(R.color.basic_color))
            .padding(7.dp)
            .clickable { navController.navigate("editProfile") }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                stringResource(id = R.string.Edit_Profile),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun ContactInfo(modifier: Modifier = Modifier){

    Text(
        stringResource(id = R.string.ContactInformation),
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )

}

@Composable
fun Info(modifier: Modifier = Modifier){

    Row(
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Camera Icon",
            tint = Color.Gray,
            modifier = Modifier.size(30.dp)
        )
        Column {
            Text(
                stringResource(id = R.string.Email),
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 10.dp),
            )
            //email from database
            Text(
                "nada@gmail.com",
                fontSize = 17.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp),
            )
        }



    }
}


@Composable
fun Setting(modifier: Modifier, localDirection: LayoutDirection){

    val context = LocalContext.current

    Row(modifier=modifier) {

        Photo(id=R.drawable.ic_setting)


        var showDialog by remember { mutableStateOf(false) }
        var selectedTheme by remember { mutableStateOf("Light") }
        var selectedLanguage by remember { mutableStateOf(Locale.getDefault().language) }


        Column(
            modifier = Modifier
            ,horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(id = R.string.Settings),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clickable { showDialog = true }
            )

            if (showDialog) {

                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(stringResource(id = R.string.Settings)) },
                    text = {
                        Column {
                            Text(text = stringResource(id = R.string.Themes), fontWeight = FontWeight.Bold)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = selectedTheme == stringResource(id = R.string.Light),
                                    onClick = { selectedTheme = "Light"
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = colorResource(R.color.basic_color),
                                    )
                                )
                                Text(stringResource(id = R.string.Light), modifier = Modifier.padding(start = 8.dp))
                                Spacer(modifier = Modifier.width(16.dp))
                                RadioButton(
                                    selected = selectedTheme == stringResource(id = R.string.Dark),
                                    onClick = { selectedTheme = "Dark"
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = colorResource(R.color.basic_color),
                                    )
                                )
                                Text(stringResource(id = R.string.Dark), modifier = Modifier.padding(start = 8.dp))
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(stringResource(id = R.string.Languages), fontWeight = FontWeight.Bold)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = selectedLanguage =="en",
                                    onClick = { selectedLanguage ="en"
                                        UpdateLocale(context,"en")


                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = colorResource(R.color.basic_color),
                                    )
                                )
                                Text(stringResource(id = R.string.English), modifier = Modifier.padding(start = 8.dp))
                                Spacer(modifier = Modifier.width(16.dp))
                                RadioButton(
                                    selected = selectedLanguage =="ar",
                                    onClick = { selectedLanguage ="ar"
                                        UpdateLocale(context,"ar")
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = colorResource(R.color.basic_color),
                                    )
                                )
                                Text(stringResource(id = R.string.Arabic), modifier = Modifier.padding(start = 8.dp))
                            }
                        }
                    },

                    confirmButton = {


                        Button(
                            onClick = {
                                showDialog = false

                            },
                            modifier = Modifier.padding(end = if (localDirection == LayoutDirection.Rtl) 8.dp else 0.dp) ,
                            colors = ButtonDefaults.buttonColors(
                                colorResource( R.color.basic_color)
                            )
                        ) {
                            Text(stringResource(id = R.string.Save))
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false },
                            modifier = Modifier.padding(end = if (localDirection == LayoutDirection.Rtl) 8.dp else 0.dp) ,

                            colors = ButtonDefaults.buttonColors(
                                colorResource( R.color.basic_color)
                            )) {
                            Text(stringResource(id = R.string.Cancel))
                        }
                    }
                )
            }
        }
    }
}



@Composable
fun Notification(modifier: Modifier){
    Row(modifier=modifier) {
        Photo(R.drawable.ic_notification)

        Text(
            stringResource(id = R.string.Notification),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier= Modifier.padding(horizontal = 10.dp)
        )

    }
}
@Composable
fun Privacy(modifier: Modifier){
    Row(modifier=modifier) {
        Photo(R.drawable.ic_privacy)

        Text(
            stringResource(id = R.string.Privacy),
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier= Modifier.padding(horizontal = 10.dp)
        )

    }
}

@Composable
fun AddAccount(modifier: Modifier, localDirection: LayoutDirection){
    Row(modifier=modifier, verticalAlignment = Alignment.CenterVertically) {
        val image = painterResource(id = R.drawable.addaccount)

        Image(
            painter = image,
            contentDescription = "Circular Image",
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

        var showDialog by remember { mutableStateOf(false) }
        var email by remember { mutableStateOf("") }  // لتخزين قيمة البريد الإلكتروني

        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(id = R.string.Add_another_account),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { showDialog = true }
            )

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(stringResource(id = R.string.Add_another_account))
                    },
                    text = {
                        Column {
                            Text(stringResource(id = R.string.Enter_Email))
                            TextField(
                                value = email,
                                onValueChange = { email = it },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                        }
                    },
                    confirmButton = {
                        Button (onClick = {
                            // قم بإضافة الحساب هنا
                            showDialog = false
                            // منطق حفظ الحساب يمكن إضافته هنا
                        },
                            modifier = Modifier.padding(end = if (localDirection == LayoutDirection.Rtl) 8.dp else 0.dp) ,
                            colors = ButtonDefaults.buttonColors(
                                colorResource( R.color.basic_color)
                            )) {
                            Text(stringResource(id = R.string.Add))
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false },
                            modifier = Modifier.padding(end = if (localDirection == LayoutDirection.Rtl) 8.dp else 0.dp) ,
                            colors = ButtonDefaults.buttonColors(
                                colorResource( R.color.basic_color)
                            )) {
                            Text(stringResource(id = R.string.Cancel))
                        }
                    }
                )
            }
        }

    }


}

@Composable
fun SignOut(modifier: Modifier, localDirection: LayoutDirection) {
    Row(modifier = modifier) {
        Photo(R.drawable.ic_logout)

        var showDialog by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(id = R.string.SignOut),
                fontSize = 20.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { showDialog = true }
            )

            if (showDialog) {

                AlertDialog(

                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(stringResource(id = R.string.SignOut))
                    },
                    text = {
                        Text(stringResource(id = R.string.Are_you_sure_you_want_to_sign_out))
                    },
                    confirmButton = {

                        Button(
                            onClick = {
                                showDialog = false
                                // Handle sign-out logic here
                            },
                            modifier = Modifier.padding(end = if (localDirection == LayoutDirection.Rtl) 8.dp else 0.dp) ,
                            colors = ButtonDefaults.buttonColors(
                                colorResource(R.color.basic_color)
                            )
                        ) {
                            Text(stringResource(id = R.string.Yes))
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialog = false },
                            modifier = Modifier.padding(end = if (localDirection == LayoutDirection.Rtl) 8.dp else 0.dp) ,
                            colors = ButtonDefaults.buttonColors(
                                colorResource(R.color.basic_color)
                            )
                        ) {
                            Text(stringResource(id = R.string.No))
                        }
                    }

                )
            }
        }
    }
}



@Composable
fun Photo(id:Int){
    val image = painterResource(id =id)

    Image(
        painter = image,
        contentDescription = "Circular Image",
        modifier = Modifier.size(24.dp)

    )
}

fun UpdateLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)

    val activity = context as? Activity
    activity?.recreate()

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreen(navController = NavController(LocalContext.current))


}