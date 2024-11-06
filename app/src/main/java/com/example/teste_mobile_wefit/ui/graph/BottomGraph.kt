package com.example.teste_mobile_wefit.ui.graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teste_mobile_wefit.constants.AppConstants
import com.example.teste_mobile_wefit.ui.navigation.BottomNav

@Composable
fun BottomGraph() {
    val navController = rememberNavController()

    var navigationSelectedItem by remember { mutableIntStateOf(AppConstants.INITIAL_BOTTOM_SCREEN) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNav().BottomBarItems(
                navigationSelectedItem = navigationSelectedItem,
                onClick = { index, navigationItem ->
                    navigationSelectedItem = index

                    navController.navigate(navigationItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = AppConstants.GRAPH.HOME,
            modifier = Modifier.padding(paddingValues = paddingValues),
        ) {
            composable(route = AppConstants.GRAPH.HOME) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Text(text = "home")
                }
            }

            composable(route = AppConstants.GRAPH.CART) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Text(text = "cart")
                }
            }

            composable(route = AppConstants.GRAPH.PROFILE) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Text(text = "profile")
                }
            }
        }
    }
}