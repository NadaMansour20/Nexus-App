package com.training.graduation.screens

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.training.graduation.R
import com.training.graduation.navigation.BottomNavigationBar

@Composable
fun HomeScreen(modifier: Modifier, navController: NavController, innerpadding: PaddingValues) {
    Box(modifier = modifier.padding(innerpadding).fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        val scrollState = rememberScrollState()
        var showDialog by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SearchBar(
                    onSearch = { query -> println("Search query: $query") },
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu Icon",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { },
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                onClick = { showDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .height(120.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(Color.Blue.copy(alpha = 0.15f))
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_video),
                            contentDescription = "null",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Black
                        )
                        Text(stringResource(R.string.start_meeting), fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Card(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp)
                        .height(120.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = CardDefaults.cardColors(Color(0xF4C2C2).copy(alpha = 1f))
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_schedule),
                                contentDescription = "null",
                                modifier = Modifier.size(20.dp),
                                tint = Color.Black
                            )
                            Text(stringResource(R.string.schedule), fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Card(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                        .height(120.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = CardDefaults.cardColors(Color.Gray.copy(alpha = 0.1f))
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val image = painterResource(id = R.drawable.ai_assest)

                            Image(
                                painter = image,
                                contentDescription = "ai asset",
                                modifier = Modifier.size(60.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(stringResource(R.string.comming_soon), fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
        if (showDialog) {
            var isCameraChecked by remember { mutableStateOf(false) }
            var isMicrophoneChecked by remember { mutableStateOf(false) }
            var isFormChecked by remember { mutableStateOf(false) }
            var isChatbotChecked by remember { mutableStateOf(false) }

            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Meeting Settings", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                text = {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text("Access Camera", modifier = Modifier.weight(1f))
                            Switch(
                                checked = isCameraChecked,
                                onCheckedChange = { isCameraChecked = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text("Access Microphone", modifier = Modifier.weight(1f))
                            Switch(
                                checked = isMicrophoneChecked,
                                onCheckedChange = { isMicrophoneChecked = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text("Create Form", modifier = Modifier.weight(1f))
                            Switch(
                                checked = isFormChecked,
                                onCheckedChange = { isFormChecked = it }
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text("Allow Chatbot", modifier = Modifier.weight(1f))
                            Switch(
                                checked = isChatbotChecked,
                                onCheckedChange = { isChatbotChecked = it }
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Start Meeting")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(70.dp))
        BottomNavigationBar(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.search),
    onSearch: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onSearch(it)
        },
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            focusedBorderColor = Color(0xFF3533CD),
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Black
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp),
        shape = RoundedCornerShape(25.dp),
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier, navController = NavController(LocalContext.current), innerpadding = PaddingValues())
}
