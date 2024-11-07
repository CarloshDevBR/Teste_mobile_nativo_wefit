package com.example.teste_mobile_wefit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teste_mobile_wefit.ui.graph.RootGraph
import com.example.teste_mobile_wefit.ui.theme.AppTheme
import com.example.teste_mobile_wefit.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme(dynamicColor = false) {
                ControllerScreens()
            }
        }
    }
}

@Composable
fun ControllerScreens() {
    val mainViewModel = viewModel(MainViewModel::class.java)

    LaunchedEffect(Unit) {
        mainViewModel.attCartState()
    }

    RootGraph(mainViewModel = mainViewModel)
}