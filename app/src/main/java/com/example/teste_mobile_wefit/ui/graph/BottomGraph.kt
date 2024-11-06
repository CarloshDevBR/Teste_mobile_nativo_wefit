package com.example.teste_mobile_wefit.ui.graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teste_mobile_wefit.constants.AppConstants
import com.example.teste_mobile_wefit.ui.composables.TopBar
import com.example.teste_mobile_wefit.ui.navigation.BottomNav
import com.example.teste_mobile_wefit.ui.screen.cart.CartSCreen
import com.example.teste_mobile_wefit.ui.screen.home.HomeScreen
import com.example.teste_mobile_wefit.viewmodel.MainViewModel

@Composable
fun BottomGraph(mainViewModel: MainViewModel, rootNavController: NavController) {
    val isVisibleBottomBar by mainViewModel.isVisibleBottomBar.collectAsState()

    val bottomNavController = rememberNavController()

    var navigationSelectedItem by remember { mutableIntStateOf(AppConstants.INITIAL_BOTTOM_SCREEN) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = {
            if (isVisibleBottomBar) {
                BottomNav().BottomBarItems(
                    navigationSelectedItem = navigationSelectedItem,
                    onClick = { index, navigationItem ->
                        navigationSelectedItem = index

                        bottomNavController.navigate(navigationItem.route) {
                            popUpTo(bottomNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = AppConstants.GRAPH.BOTTOM_NAV.HOME,
            modifier = Modifier.padding(paddingValues = paddingValues),
        ) {
            composable(route = AppConstants.GRAPH.BOTTOM_NAV.CART) {
                CartSCreen(rootNavController = rootNavController)
            }

            composable(route = AppConstants.GRAPH.BOTTOM_NAV.HOME) {
                HomeScreen(mainViewModel = mainViewModel)
            }

            composable(route = AppConstants.GRAPH.BOTTOM_NAV.PROFILE) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Text(text = "profile")
                }
            }
        }
    }
}