package com.training.graduation.screens.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.training.graduation.R
import com.training.graduation.screens.notification.NotificationScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController){

    //add image from gallery
    val imageUri = rememberSaveable { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri.value = it.toString()
        }
    }

    val painter = rememberAsyncImagePainter(
        imageUri.value.ifEmpty { R.drawable.image11 }
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundContent(navController)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 150.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 50.dp
            ),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                EditPhoto(modifier =Modifier.padding(bottom = 20.dp),launcher)

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(25.dp),
                    label = { Text(stringResource(R.string.username)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(25.dp),
                    label = { Text(stringResource(R.string.Email)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp, start = 25.dp, end = 25.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(25.dp),
                    label = { Text(stringResource(R.string.Password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp, start = 25.dp, end = 25.dp)
                )

                Button(
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 80.dp, end = 80.dp, top = 50.dp)
                        .height(40.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF000000), Color(0xFF3533CD)),
                                start = Offset(
                                    0f,
                                    0f
                                ),
                                end = Offset(
                                    Float.POSITIVE_INFINITY,
                                    Float.POSITIVE_INFINITY
                                )
                            ),
                            shape = RoundedCornerShape(30.dp)
                        ),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White

                    ),

                    ) {
                    Text(
                        text = "Edit",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                //يحرك الصورة عموديًا بمقدار 100dp، مما يجعل جزءًا من الصورة يدخل داخل البطاقة.
                //القيمة السالبة تحركها للأعلى، والقيمة الموجبة تحركها للأسفل.
                .offset(y = 100.dp)
                .zIndex(1f) // للتأكد من أنها تظهر فوق الـ Card
        ) {
            ProfileImage(painter)
        }
    }


}

@Composable
fun ProfileImage(painter: AsyncImagePainter) {

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = "Circular Image",
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}

@Composable
fun EditPhoto(modifier: Modifier,launcher: androidx.activity.compose.ManagedActivityResultLauncher<String, Uri?>) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(7.dp)
            .clickable {
                launcher.launch("image/*")
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Photo(id= R.drawable.ic_addphoto)

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                stringResource(R.string.Edit_photo),
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun BackgroundContent(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.basic_color))

    ) {
        Column( modifier = Modifier.padding(top = 30.dp, start = 10.dp),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.padding(end = 8.dp)
                    )

                }
                Text(
                    stringResource(R.string.edit_your_profile),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Edit(){

    val navController = rememberNavController()
    Profile(navController = navController)
}
