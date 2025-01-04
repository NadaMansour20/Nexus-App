package com.training.graduation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.training.graduation.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun Profile(){

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundContent()

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
                Spacer(modifier = Modifier.height(80.dp)) // مساحة للصورة
                EditPhoto(Modifier.padding(bottom = 20.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    ),
                    label = { Text(stringResource(R.string.username)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    ),
                    label = { Text(stringResource(R.string.Email)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    ),
                    label = { Text(stringResource(R.string.Password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                    },
                    colors = ButtonDefaults.buttonColors(
                        colorResource(R.color.basic_color)
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(150.dp)
                ) {
                    Text(stringResource(R.string.edit), fontSize = 20.sp)
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
            ProfileImage(Modifier.size(120.dp))
        }
    }

}

@Composable
fun ProfileImage(modifier: Modifier) {
    val image = painterResource(id = R.drawable.image11)

    Image(
        painter = image,
        contentDescription = "Circular Image",
        modifier =modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}

@Composable
fun EditPhoto(modifier: Modifier) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(7.dp)
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
fun BackgroundContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.basic_color))
    )
}
