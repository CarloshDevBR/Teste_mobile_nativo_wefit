package com.example.teste_mobile_wefit.ui.graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teste_mobile_wefit.ui.navigation.BottomNavigation

@Composable
fun BottomNavGraph(rootNav: NavHostController) {
    val navController = rememberNavController()

    var navigationSelectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation().BottomBarItems(
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
            startDestination = "home",
            modifier = Modifier.padding(paddingValues = paddingValues),
        ) {
            composable("home") {
                Text(text = "home")
            }

            composable("cart") {
                Text(text = "cart")
            }

            composable("account") {
                Text(text = "account")
            }
        }
    }
}