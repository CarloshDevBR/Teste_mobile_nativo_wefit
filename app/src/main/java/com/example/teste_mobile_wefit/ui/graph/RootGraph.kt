package com.example.teste_mobile_wefit.ui.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teste_mobile_wefit.constants.AppConstants
import com.example.teste_mobile_wefit.viewmodel.MainViewModel

@Composable
fun RootGraph(mainViewModel: MainViewModel) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = AppConstants.GRAPH.BOTTOM_NAV.INDEX
    ) {
        composable(route = AppConstants.GRAPH.BOTTOM_NAV.INDEX) {
            BottomGraph(mainViewModel = mainViewModel, rootNavController = rootNavController)
        }
    }
}