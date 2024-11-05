package com.example.teste_mobile_wefit.ui.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class BottomNavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
    val private: Boolean
)

class BottomNavigation {
    private fun bottomListNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "cart",
                icon = Icons.Filled.Search,
                route = "cart",
                private = false
            ),
            BottomNavigationItem(
                label = "home",
                icon = Icons.Filled.Home,
                route = "home",
                private = false
            ),
            BottomNavigationItem(
                label = "account",
                icon = Icons.Outlined.ShoppingCart,
                route = "account",
                private = true
            )
        )
    }

    @Composable
    fun BottomBarItems(
        navigationSelectedItem: Int,
        onClick: (index: Int, navigationItem: BottomNavigationItem) -> Unit
    ) {
        NavigationBar(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                )
        ) {
            BottomNavigation()
                .bottomListNavigationItems()
                .forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = index == navigationSelectedItem,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = { onClick(index, navigationItem) }
                    )
                }
        }
    }
}