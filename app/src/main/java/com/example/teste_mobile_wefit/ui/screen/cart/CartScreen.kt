package com.example.teste_mobile_wefit.ui.screen.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.teste_mobile_wefit.R
import com.example.teste_mobile_wefit.constants.AppConstants
import com.example.teste_mobile_wefit.ui.composables.EmptyScreen
import com.example.teste_mobile_wefit.ui.composables.Header
import com.example.teste_mobile_wefit.ui.screen.cart.composables.CardItem
import com.example.teste_mobile_wefit.utils.AppDate
import com.example.teste_mobile_wefit.utils.Format
import com.example.teste_mobile_wefit.viewmodel.CartViewModel
import com.example.teste_mobile_wefit.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun CartSCreen(rootNavController: NavController, mainViewModel: MainViewModel) {
    val viewModel = viewModel(CartViewModel::class.java)

    var finalized by remember { mutableStateOf(false) }

    var dateFinalizedNow by remember { mutableStateOf(Pair("", "")) }

    val cart by mainViewModel.cart.collectAsState()

    val cartItems by mainViewModel.cartItems.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    var showDialog by remember { mutableStateOf(false) }

    val annotatedString = buildAnnotatedString {
        append("Compra realizada em ")

        withStyle(
            style = SpanStyle(fontWeight = MaterialTheme.typography.bodySmall.fontWeight)
        ) {
            append(dateFinalizedNow.first)
        }

        append(" às ")

        withStyle(style = SpanStyle(fontWeight = MaterialTheme.typography.bodySmall.fontWeight)) {
            append(dateFinalizedNow.second)
        }
    }

    if (showDialog) {
        AlertDialog(
            shape = RoundedCornerShape(4.dp),
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Atenção")
            },
            text = {
                Text(text = "Tem certeza de que deseja finalizar o pedido?")
            },
            confirmButton = {
                Button(
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        coroutineScope.launch {
                            val date = AppDate().getDateToday()
                            val hours = AppDate().getHoursToday()

                            viewModel.finishCart(
                                cartId = cart!!.id!!,
                                dateFinalized = "$date $hours"
                            ) {
                                dateFinalizedNow = Pair(date, hours)

                                finalized = true

                                mainViewModel.attCartState()
                            }
                        }

                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                ) {
                    Text(text = "Confirmar", color = MaterialTheme.colorScheme.onTertiary)
                }
            },
            dismissButton = {
                Button(
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    onClick = { showDialog = false }) {
                    Text(text = "Cancelar", color = MaterialTheme.colorScheme.onTertiary)
                }
            }
        )
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .then(
                    if ((cart == null || cartItems.isNullOrEmpty())) {
                        Modifier
                    } else {
                        Modifier.verticalScroll(rememberScrollState())
                    }
                )
        ) {
            if (!finalized) {
                Header(title = "Carrinho de compras")
            }

            when {
                cart != null && !cartItems.isNullOrEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.onTertiary)
                            .padding(16.dp)
                    ) {
                        cartItems!!.map { cartItem ->
                            CardItem(
                                data = cartItem,
                                onChangeQuantity = { newQuantity ->
                                    coroutineScope.launch {
                                        viewModel.updateQuantity(cartItem, newQuantity)
                                        mainViewModel.attCartState()
                                    }
                                },
                                onDelete = {
                                    coroutineScope.launch {
                                        viewModel.deleteItem(cartItem)
                                        mainViewModel.attCartState()
                                    }
                                }
                            )
                        }

                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.tertiary
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total",
                                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                color = MaterialTheme.colorScheme.tertiary
                            )

                            Text(
                                text = Format().formatCurrencyToBRL(cart!!.total),
                                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                                fontSize = 24.sp,
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                color = MaterialTheme.colorScheme.background
                            )
                        }

                        Spacer(modifier = Modifier.padding(vertical = 8.dp))

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            onClick = { showDialog = true }
                        ) {
                            Text(
                                text = "FINALIZAR PEDIDO",
                                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                    }
                }

                finalized -> {
                    EmptyScreen(
                        imageModifier = Modifier
                            .width(238.dp)
                            .height(374.dp)
                            .padding(bottom = 24.dp),
                        modifier = Modifier.padding(top = 24.dp).padding(bottom = 24.dp),
                        title = "Compra realizada com sucesso!",
                        subtitle = annotatedString,
                        drawableResId = R.drawable.finalized_cart,
                        textButton = "Voltar à Home",
                        actionButton = {
                            rootNavController.navigate(AppConstants.GRAPH.BOTTOM_NAV.INDEX) {
                                popUpTo(rootNavController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }

                (cart == null || cartItems.isNullOrEmpty()) && !finalized -> {
                    EmptyScreen(
                        imageModifier = Modifier
                            .width(178.dp)
                            .height(344.dp)
                            .padding(bottom = 24.dp),
                        modifier = Modifier.padding(bottom = 24.dp),
                        title = "Parece que não há nada por aqui :(",
                        drawableResId = R.drawable.go_home,
                        textButton = "Voltar à Home",
                        actionButton = {
                            rootNavController.navigate(AppConstants.GRAPH.BOTTOM_NAV.INDEX) {
                                popUpTo(rootNavController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }
        }
    }
}