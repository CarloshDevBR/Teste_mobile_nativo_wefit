package com.example.teste_mobile_wefit.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teste_mobile_wefit.ui.composables.GradientCircularLoading
import com.example.teste_mobile_wefit.viewmodel.HomeViewModel

@Composable
fun HomeScreen() {
    val viewModel = viewModel(HomeViewModel::class.java)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GradientCircularLoading()
        }
    }
}