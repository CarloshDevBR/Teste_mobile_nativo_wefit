package com.example.teste_mobile_wefit.ui.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teste_mobile_wefit.R
import com.example.teste_mobile_wefit.constants.AppConstants
import com.example.teste_mobile_wefit.ui.composables.EmptyScreen
import com.example.teste_mobile_wefit.ui.composables.Header
import com.example.teste_mobile_wefit.ui.screen.cart.composables.CardItem
import com.example.teste_mobile_wefit.viewmodel.MainViewModel

@Composable
fun CartSCreen(rootNavController: NavController, mainViewModel: MainViewModel) {
    val cartItems by mainViewModel.cartItems.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Header(title = "Carrinho de compras")

            if (!cartItems.isNullOrEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.onTertiary)
                        .padding(16.dp)
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(cartItems!!.size) { index ->
                            val item = cartItems!![index]

                            CardItem(item)
                        }
                    }

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            } else {
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
}