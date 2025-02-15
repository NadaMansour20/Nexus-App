package com.training.graduation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.training.graduation.R



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = NavController(LocalContext.current))

}


@Composable
fun BottomNavigationBar(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(20.dp)
                .height(95.dp)
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            NavigationBar(
                modifier = Modifier.background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Black, Color(0xFF3533CD)),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    )
                ),
                containerColor = Color.Transparent,
                contentColor = Color.White,

                ) {
                val currentRoute = navController.currentDestination?.route

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.home_icon),
                            contentDescription = "Home",
                            tint = if (currentRoute == "homescreen") Color.White else Color.White,
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .size(25.dp)
                        )
                    },
                    label = {
                        if (currentRoute == "homescreen") {
                            Text(
                                text = stringResource(R.string.home),
                                color = Color.White
                            )
                        }
                    },
                    selected = currentRoute == "homescreen",
                    onClick = {
                        navController.navigate("homescreen") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.message_icon),
                            contentDescription = "Chat",
                            tint = if (currentRoute == "chat") Color.White else Color.White,
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .size(25.dp)
                        )
                    },
                    label = {
                        if (currentRoute == "chat") {
                            Text(
                                text = stringResource(R.string.chat),
                                color = Color.White
                            )
                        }
                    },
                    selected = currentRoute == "chat",
                    onClick = {
                        navController.navigate("chat") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.group_icon),
                            contentDescription = "Group",
                            tint = if (currentRoute == "group") Color.White else Color.White,
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .size(25.dp)
                        )
                    },
                    label = {
                        if (currentRoute == "group") {
                            Text(
                                text = stringResource(R.string.group),
                                color = Color.White
                            )
                        }
                    },
                    selected = currentRoute == "group",
                    onClick = {
                        navController.navigate("group") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.profile_icon),
                            contentDescription = "User Profile",
                            tint = if (currentRoute == "userprofile") Color.White else Color.White,
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .size(25.dp)
                        )
                    },
                    label = {
                        if (currentRoute == "userprofile") {
                            Text(
                                text = stringResource(R.string.profile),
                                color = Color.White
                            )
                        }
                    },
                    selected = currentRoute == "userprofile",
                    onClick = {
                        navController.navigate("userprofile") {
                            popUpTo(0) { inclusive = true }
                        }


                    }
                )
            }
        }


    }
}


