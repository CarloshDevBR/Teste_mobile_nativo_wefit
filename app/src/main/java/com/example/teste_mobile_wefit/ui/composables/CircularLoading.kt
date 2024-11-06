package com.example.teste_mobile_wefit.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularLoading(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSecondary,
    strokeWith: Dp = 2.dp
) {
    CircularProgressIndicator(
        modifier = modifier.size(62.dp),
        color = color,
        strokeWidth = strokeWith
    )
}