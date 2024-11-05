package com.example.teste_mobile_wefit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.teste_mobile_wefit.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme(dynamicColor = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Text(text = "teste")
                }
            }
        }
    }
}