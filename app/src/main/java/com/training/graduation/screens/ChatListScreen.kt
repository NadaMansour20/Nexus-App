package com.training.graduation.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.training.graduation.R
import java.util.Date
import java.util.Locale

data class Chat(
    val userEmail: String,
    val userName: String,
    val lastMessage: String,
    val lastMessageTime: Long,
    val unreadMessages: Int
)
@Composable
fun ChatListScreen(navController: NavController){
    val chats = remember { mutableStateListOf<Chat>() }
    val query= remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        chats.clear()


    }
    Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(130.dp))
        SearchBar(onSearch = { query ->
            println("Search query: $query")
        },
            modifier = Modifier.weight(1f))
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(chats.filter { it.userName.contains(query.value, ignoreCase = true) }) { chat ->

                ChatItem(chat, navController)
            }
        }
    }

}
//@Composable
//fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
//    TextField(
//        value = query,
//        onValueChange = onQueryChanged,
//        label = { Text("Search Contacts") },
//        modifier = Modifier.fillMaxWidth(),
//        singleLine = true
//    )
//}

@Composable
fun ChatItem(chat: Chat, navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                // الانتقال إلى شاشة الدردشة الخاصة
                navController.navigate("chat/${chat.userEmail}")
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(Icons.Default.Chat, contentDescription = "Chat Icon")
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = chat.userName, fontWeight = FontWeight.Bold)
            Text(text = chat.lastMessage, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        if (chat.unreadMessages > 0) {
            Badge(
                modifier = Modifier.padding(start = 8.dp),
                content = { Text(text = chat.unreadMessages.toString()) }
            )
        }
        Text(text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(chat.lastMessageTime)))

    }
}

//data class Chat(val userName: String, val lastMessage: String, val time: String)
//
//@Composable
//fun ChatListScreen() {
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Image(
//            painter = painterResource(id = R.drawable.background),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.FillBounds
//        )
//        val chats = remember {
//            mutableStateListOf(
//                Chat("John", "Hey, how are you?", "2:15 PM"),
//                Chat("Sarah", "Let's meet at 5!", "1:45 PM"),
//                Chat("Emily", "Did you finish the report?", "12:30 PM"),
//                Chat("Michael", "See you tomorrow!", "11:00 AM")
//            )
//        }
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            Spacer(modifier = Modifier.height(30.dp))
//            Text(
//                text = "Chats",
//                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//            LazyColumn(modifier = Modifier.weight(1f)) {
//                items(chats) { chat ->
//                    ChatItem(chat = chat)
//                }
//            }
//        }
//    }
//}
//@Composable
//fun ChatItem(chat: Chat){
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .background(Color(0xFFF1F1F1), shape = RoundedCornerShape(8.dp))
//            .padding(16.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Default.Chat,
//            contentDescription = "Chat Icon",
//            tint = Color.Blue,
//            modifier = Modifier.size(40.dp)
//        )
//        Spacer(modifier = Modifier.width(16.dp))
//        Column(
//            modifier = Modifier.weight(1f)
//        ) {
//            Text(
//                text = chat.userName,
//                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = chat.lastMessage,
//                style = TextStyle(fontSize = 14.sp, color = Color.Gray)
//            )
//        }
//        Text(
//            text = chat.time,
//            style = TextStyle(fontSize = 12.sp, color = Color.Gray),
//            modifier = Modifier.align(Alignment.CenterVertically)
//        )
//
//    }
//}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatListScreenPreview() {
    ChatListScreen(navController = NavController(LocalContext.current))
}