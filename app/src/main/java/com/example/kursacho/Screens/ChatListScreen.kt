package com.example.kursacho.Screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.kursacho.LCViewModel

@Composable
fun ChatListScreen(navController: NavController, vm: LCViewModel) {

    Text(text = "chatlistscreen")
    BottomNavigationMenu(selectedItem = BottomNavigationItem.CHATLIST, navController = navController)
}