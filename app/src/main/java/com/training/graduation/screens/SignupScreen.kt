package com.training.graduation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.training.graduation.R


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignupScreenPreview(){
    SignupScreen(modifier = Modifier, navController= NavController(LocalContext.current), innerpadding = PaddingValues())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(modifier: Modifier, navController: NavController, innerpadding: PaddingValues){
    var selectedRole by remember { mutableStateOf("User") }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.lets),
                color = Color.Black,
                fontSize = 40.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp)

            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = stringResource(R.string.create),
                color = Color(0xFF3533CD),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp)
            )
            Text(
                text = stringResource(R.string.your),
                color = Color(0xFF3533CD),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp)
            )
            Text(
                text = stringResource(R.string.account),
                color = Color(0xFF3533CD),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.select_your_role),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 30.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedRole == stringResource(R.string.user),
                    onClick = { "User".also { selectedRole = it } },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF3533CD),
                        unselectedColor = Color.Gray
                    )
                )
                Text(text = stringResource(R.string.user), fontSize = 18.sp)
                Spacer(modifier = Modifier.width(20.dp))
                RadioButton(
                    selected = selectedRole == stringResource(R.string.instructor),
                    onClick = { "Foundation".also { selectedRole = it } },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF3533CD),
                        unselectedColor = Color.Gray
                    )
                )
                Text(text = stringResource(R.string.instructor), fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.username)) },
                value = "",
                onValueChange = {},
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color.LightGray
//                ),
                leadingIcon = {
                    Icon(
                        ImageVector.vectorResource(R.drawable.ic_user),
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)

            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.enter_your_email)) },
                value = "",
                onValueChange = {},
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color.LightGray
//                ),
                leadingIcon = {
                    Icon(
                        ImageVector.vectorResource(R.drawable.ic_email),
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.enter_your_password)) },
                value = "",
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {},
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color.LightGray
//                ),
                leadingIcon = {
                    Icon(
                        ImageVector.vectorResource(R.drawable.ic_lock),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    Icon(
                        ImageVector.vectorResource(R.drawable.ic_eye),
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                label = { Text(stringResource(R.string.confirm_your_password)) },
                value = "",
                onValueChange = {},
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color.LightGray
//                ),
                leadingIcon = {
                    Icon(
                        ImageVector.vectorResource(R.drawable.ic_lock),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    Icon(
                        ImageVector.vectorResource(R.drawable.ic_eye),
                        contentDescription = null
                    )
                },
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    navController.navigate("loginscreen"){
                        popUpTo("signupscreen")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .height(50.dp)
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
                    text = stringResource(R.string.sign_up),
                    color = Color.White,

                    fontSize = 15.sp
                )

            }


    }
}
