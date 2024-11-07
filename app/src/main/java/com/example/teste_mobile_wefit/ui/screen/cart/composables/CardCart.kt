package com.example.teste_mobile_wefit.ui.screen.cart.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.RemoveCircleOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.teste_mobile_wefit.entity.CartItemEntity
import com.example.teste_mobile_wefit.ui.composables.ImageAsync
import com.example.teste_mobile_wefit.utils.Format
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CardItem(
    data: CartItemEntity,
    onChangeQuantity: (quantity: Int) -> Unit,
    onDelete: () -> Unit
) {
    var quantity by remember { mutableIntStateOf(data.quantity) }

    var showDialog by remember { mutableStateOf(false) }

    var debounceJob by remember { mutableStateOf<Job?>(null) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(quantity) {
        debounceJob?.cancel()

        debounceJob = coroutineScope.launch {
            delay(250L)

            onChangeQuantity(quantity)
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
                Text(text = "Tem certeza de que deseja remover ${data.name} do carrinho?")
            },
            confirmButton = {
                Button(
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        onDelete()

                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                ) {
                    Text(text = "Remover", color = MaterialTheme.colorScheme.onTertiary)
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

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageAsync(
                url = data.image, modifier = Modifier
                    .width(56.dp)
                    .height(72.dp)
            )

            Column {
                Text(
                    text = data.name,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily
                )

                Text(
                    text = buildAnnotatedString {
                        append("Adicionado em ")

                        withStyle(style = SpanStyle(fontWeight = MaterialTheme.typography.bodySmall.fontWeight)) {
                            append(data.date)
                        }
                    },
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily
                )
            }

            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .clickable { showDialog = true },
                imageVector = Icons.Filled.Delete,
                contentDescription = "delete item",
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        if (quantity > 1) {
                            quantity--
                        }
                    },
                    imageVector = Icons.Outlined.RemoveCircleOutline,
                    contentDescription = "remove",
                    tint = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Row(
                    modifier = Modifier
                        .width(60.dp)
                        .height(26.dp)
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(4.dp)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = quantity.toString(),
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.padding(4.dp))

                Icon(
                    modifier = Modifier.clickable { quantity++ },
                    imageVector = Icons.Outlined.AddCircleOutline,
                    contentDescription = "add",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }

            Column {
                Text(
                    text = "SUBTOTAL",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    textAlign = TextAlign.End
                )

                Text(
                    text = Format().formatCurrencyToBRL(data.subtotal),
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}