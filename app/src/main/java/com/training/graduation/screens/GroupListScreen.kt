package com.training.graduation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.training.graduation.navigation.BottomNavigationBar

@Composable
fun GroupListScreen(navController: NavController){
    BottomNavigationBar(navController = navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GroupListScreenPreview() {
    GroupListScreen(navController = NavController(LocalContext.current))
}