package com.example.kursacho.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.kursacho.LCViewModel

@Composable
fun StatusScreen(navController: NavController, vm: LCViewModel) {
    BottomNavigationMenu(selectedItem = BottomNavigationItem.STATUSLIST, navController = navController)
}