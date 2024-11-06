package com.example.teste_mobile_wefit.ui.screen.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teste_mobile_wefit.R
import com.example.teste_mobile_wefit.constants.AppConstants
import com.example.teste_mobile_wefit.ui.composables.EmptyScreen
import com.example.teste_mobile_wefit.ui.composables.Header

@Composable
fun CartSCreen(rootNavController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Header(title = "Carrinho de compras")

            EmptyScreen(
                modifier = Modifier.padding(bottom = 24.dp),
                title = "Parece que não há nada por aqui :(",
                drawableResId = R.drawable.go_home,
                textButton = "Voltar á Home",
                actionButton = {
                    rootNavController.navigate(AppConstants.GRAPH.BOTTOM_NAV.INDEX) {
                        popUpTo(rootNavController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}