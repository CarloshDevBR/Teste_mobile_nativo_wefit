package com.example.teste_mobile_wefit.model.app

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItemModel(
    val label: String,
    val icon: ImageVector,
    val route: String,
    val disabled: Boolean
)