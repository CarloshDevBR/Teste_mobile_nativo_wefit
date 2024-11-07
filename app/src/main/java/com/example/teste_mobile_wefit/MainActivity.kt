package com.example.teste_mobile_wefit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teste_mobile_wefit.ui.graph.RootGraph
import com.example.teste_mobile_wefit.ui.composables.SplashScreen
import com.example.teste_mobile_wefit.ui.theme.AppTheme
import com.example.teste_mobile_wefit.viewmodel.MainViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme(dynamicColor = false) {
                SplashScreenController()
            }
        }
    }
}

@Composable
fun SplashScreenController() {
    var showSplash by remember { mutableStateOf(true) }

    val mainViewModel = viewModel(MainViewModel::class.java)

    LaunchedEffect(Unit) {
        mainViewModel.attCartState()

        delay(3000)

        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        RootGraph(mainViewModel = mainViewModel)
    }
}
