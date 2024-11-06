package com.example.teste_mobile_wefit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.teste_mobile_wefit.ui.graph.RootGraph
import com.example.teste_mobile_wefit.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme(dynamicColor = false) {
                RootGraph()
            }
        }
    }
}