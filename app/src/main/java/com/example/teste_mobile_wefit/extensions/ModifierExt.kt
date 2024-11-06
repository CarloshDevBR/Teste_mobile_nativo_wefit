package com.example.teste_mobile_wefit.extensions

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.gradientBottomNav(): Modifier = composed {
    background(
        Brush.verticalGradient(
            colors = listOf(
                Color(0x18FFFFFF),
                Color(0x0CFFFFFF),
            ),
            startY = 0f,
            endY = 100f
        )
    )
}