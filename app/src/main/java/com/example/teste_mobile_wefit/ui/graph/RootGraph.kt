package com.example.teste_mobile_wefit.ui.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teste_mobile_wefit.constants.AppConstants

@Composable
fun RootGraph() {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = AppConstants.GRAPH.BOTTOM_NAV
    ) {
        composable(route = AppConstants.GRAPH.BOTTOM_NAV) {
            BottomGraph()
        }
    }
}