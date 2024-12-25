package com.training.graduation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.graduation.R


@Preview
@Composable
fun RecordedMeeting(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomEnd = 10.dp,
            bottomStart = 10.dp
        ),
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){

            Row(
                modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val image = painterResource(id = R.drawable.ic_video)

                Image(
                    painter = image,
                    contentDescription = "Circular Image",
                    modifier = Modifier.size(45.dp)

                )

                Column {

                    Text("Title........", maxLines = 1,
                        modifier = Modifier.padding(start = 10.dp))

                    Spacer(modifier = Modifier.padding( 2.dp))

                    Text("10 MB",modifier = Modifier.padding(start = 10.dp))
                }
                Spacer(modifier = Modifier.padding( 100.dp))


                Menu()


            }
        }
    }

}
@Composable
fun Menu() {
    var expanded by remember { mutableStateOf(false) }


    Box() {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Menu"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            DropdownMenuItem(
                text = { Text("Open", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )

            DropdownMenuItem(
                text = { Text("Download", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Delete", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Share", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Copy link", fontSize = 14.sp) },
                onClick = {
                    expanded = false
                }
            )
        }
    }
}
