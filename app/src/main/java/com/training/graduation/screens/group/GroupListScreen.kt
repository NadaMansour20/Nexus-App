package com.training.graduation.screens.group

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.training.graduation.R
import com.training.graduation.navigation.BottomNavigationBar
import com.training.graduation.screens.Menu
import com.training.graduation.screens.profile.Photo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource


// Data class for Group
data class Group(val groupName: String, val groupDescription: String, val time: String)

// Main composable screen
@Composable
fun GroupListScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val groups = remember {
            mutableStateListOf(
                Group("Learn Programming", "Join the group to learn programming languages, tutorials, and challenges.", "2:15 PM"),
                Group("Mobile Development", "A group focused on building apps with Android and iOS.", "1:45 PM"),
                Group("Data Science & AI", "Learn about data science, machine learning, and artificial intelligence.", "12:30 PM"),
                Group("Web Development", "Explore frontend and backend development, frameworks, and more.", "11:00 AM"),
                Group("Game Development", "Learn how to create games using Unity, Unreal Engine, and more.", "10:45 AM")
            )
        }


        // Layout for the Group List Screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Groups",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            com.training.graduation.screens.SearchBar(
                onSearch = { query ->
                    println("Search query: $query")
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Card()
            Spacer(modifier = Modifier.height(10.dp))
            // LazyColumn for group items
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(groups) { group ->
                    GroupItem(group = group)
                }
            }
        }

        // Bottom Navigation Bar
        BottomNavigationBar(navController = navController)
    }
}

// Composable for each Group item
@Composable
fun GroupItem(group: Group) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFF1F1F1), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        // Group Icon
        Icon(
            imageVector = Icons.Default.Group,
            contentDescription = "Group Icon",
            tint = Color.Blue,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Group Information
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = group.groupName,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = group.groupDescription,
                style = TextStyle(fontSize = 14.sp, color = Color.Gray)
            )
        }

        // Time of the last post
        Text(
            text = group.time,
            style = TextStyle(fontSize = 12.sp, color = Color.Gray),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GroupListScreenPreview() {
    GroupListScreen(navController = NavController(LocalContext.current))
}

@Composable
fun Card(){
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomEnd = 10.dp,
            bottomStart = 10.dp
        ),colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column {

            Row(
                modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray)
                        .size(60.dp) ,
                    contentAlignment = Alignment.Center


                ) {
                   Photo(R.drawable.plus,Color.DarkGray)
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    stringResource(R.string.create_new_group),
                    modifier = Modifier.padding(start = 5.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = Color.DarkGray
                    )
                )
            }
        }
    }

}
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
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            errorContainerColor = Color.White,
            focusedIndicatorColor = colorResource(R.color.basic_color),
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = Color.Black
        ),
//            containerColor = Color.White,
//            focusedBorderColor = colorResource(R.color.basic_color),
//            unfocusedBorderColor = Color.Gray,
//            cursorColor = Color.Black
//        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp),
        shape = RoundedCornerShape(25.dp),
    )
}