package com.example.teste_mobile_wefit.ui.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RootGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "bottom_nav"
    ) {
        composable(route = "bottom_nav") {
            BottomNavGraph(rootNav = navController)
        }
    }
}