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

@Composable
fun GroupListScreen(navController: NavController){
    BottomNavigationBar(navController = navController)

    Column(modifier = Modifier.padding(40.dp)) {

        Text(stringResource(R.string.create_your_own_group),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)

        )

        Spacer(modifier = Modifier.padding(10.dp))

        Card()
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