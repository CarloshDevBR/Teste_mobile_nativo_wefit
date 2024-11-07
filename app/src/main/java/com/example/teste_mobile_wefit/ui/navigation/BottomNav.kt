package com.example.teste_mobile_wefit.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.teste_mobile_wefit.constants.AppConstants
import com.example.teste_mobile_wefit.extensions.gradientBottomNav
import com.example.teste_mobile_wefit.model.app.BottomNavigationItemModel

class BottomNav {
    private fun bottomListNavItems(): List<BottomNavigationItemModel> {
        return listOf(
            BottomNavigationItemModel(
                label = "Carrinho",
                icon = Icons.Outlined.ShoppingCart,
                route = AppConstants.GRAPH.BOTTOM_NAV.CART,
                disabled = false
            ),
            BottomNavigationItemModel(
                label = "Home",
                icon = Icons.Outlined.Home,
                route = AppConstants.GRAPH.BOTTOM_NAV.HOME,
                disabled = false
            ),
            BottomNavigationItemModel(
                label = "Perfil",
                icon = Icons.Outlined.Person,
                route = AppConstants.GRAPH.BOTTOM_NAV.PROFILE,
                disabled = true
            )
        )
    }

    @Composable
    fun BottomBarItems(
        quantityCart: Int?,
        navigationSelectedItem: Int,
        onClick: (index: Int, navigationItem: BottomNavigationItemModel) -> Unit
    ) {
        NavigationBar(
            modifier = Modifier.height(56.dp),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            bottomListNavItems().forEachIndexed { index, navigationItem ->
                val isSelected = index == navigationSelectedItem

                Box(modifier = Modifier
                    .weight(1F)
                    .then(
                        if (!navigationItem.disabled) {
                            Modifier.clickable { onClick(index, navigationItem) }
                        } else {
                            Modifier
                        }
                    )
                ) {
                    if (isSelected) {
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .then(
                                if (isSelected) {
                                    Modifier.gradientBottomNav()
                                } else {
                                    Modifier
                                }
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = navigationItem.icon,
                            contentDescription = navigationItem.label,
                            tint = if (navigationItem.disabled) Color.Gray else MaterialTheme.colorScheme.onTertiary
                        )

                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                        Text(
                            text = if (navigationItem.route == AppConstants.GRAPH.BOTTOM_NAV.CART && quantityCart != null) "${navigationItem.label} ${"(${quantityCart})"}" else navigationItem.label,
                            fontWeight = FontWeight.Normal,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = if (navigationItem.disabled) Color.Gray else MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
            }
        }
    }
}