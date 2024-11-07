package com.example.teste_mobile_wefit.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.withStyle

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .width(296.dp)
                .height(296.dp)
                .fillMaxSize()
                .offset((425).dp, -400.dp)
        ) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF2E2E3A), Color(0xFF444456)),
                    center = Offset(size.width * 0.5f, size.height * 0.5f),
                    radius = size.width * 0.75f
                ),
                radius = size.width * 0.75f,
                center = Offset(0f, 0f)
            )
        }

        Text(
            text = buildAnnotatedString {
                append("Bem vindo a ")

                withStyle(
                    style = SpanStyle(
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                        fontSize = 42.sp
                    )
                ) {
                    append("\n\nWeMovies")
                }
            },
            color = Color.White,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        )

        Canvas(
            modifier = Modifier
                .width(296.dp)
                .height(296.dp)
                .fillMaxSize()
                .offset((-125).dp, 575.dp)
        ) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF2E2E3A), Color(0xFF444456)),
                    center = Offset(size.width * 0.5f, size.height * 0.5f),
                    radius = size.width * 0.75f
                ),
                radius = size.width * 0.75f,
                center = Offset(0f, 0f)
            )
        }
    }
}
