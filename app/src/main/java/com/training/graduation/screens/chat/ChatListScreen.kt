package com.training.graduation.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.training.graduation.R
import com.training.graduation.navigation.BottomNavigationBar
import com.training.graduation.screens.mainscreen.SearchBar

data class Chat(val userName: String, val lastMessage: String, val time: String)

@Composable
fun ChatListScreen(navController: NavController) {
    val chats = remember {
        mutableStateListOf(
            Chat("Dina", "Hey, how are you?", "2:15 PM"),
            Chat("Mariam", "Let's meet at 5!", "1:45 PM"),
            Chat("Nada", "Did you finish the report?", "12:30 PM"),
            Chat("Salma", "See you tomorrow!", "11:00 AM")
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Chats",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        SearchBar(
            onSearch = { query ->
                println("Search query: $query")
            }
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(chats) { chat ->
                ChatItem(chat = chat)
            }
        }

    }
    BottomNavigationBar(navController = navController)
}
@Composable
fun ChatItem(chat: Chat){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFF1F1F1), shape = RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Icon(
                painter = painterResource(id = R.drawable.message_icon),
                contentDescription = "Chat",
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .size(25.dp)
            )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chat.userName,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = chat.lastMessage,
                style = TextStyle(fontSize = 14.sp, color = Color.Gray)
            )
        }
        Text(
            text = chat.time,
            style = TextStyle(fontSize = 12.sp, color = Color.Gray),
            modifier = Modifier.align(Alignment.CenterVertically)
        )

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatListScreenPreview() {
    ChatListScreen(navController = rememberNavController())
}


