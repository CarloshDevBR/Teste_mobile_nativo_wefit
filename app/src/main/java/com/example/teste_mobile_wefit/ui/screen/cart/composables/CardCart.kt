package com.example.teste_mobile_wefit.ui.screen.cart.composables

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.RemoveCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teste_mobile_wefit.entity.CartItemEntity
import com.example.teste_mobile_wefit.ui.composables.ImageAsync
import com.example.teste_mobile_wefit.utils.Format

@Composable
fun CardItem(data: CartItemEntity) {
    Column(modifier = Modifier.padding(top = 16.dp, bottom = 20.dp)) {
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

            IconButton(onClick = {}) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete item",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.RemoveCircleOutline,
                        contentDescription = "Remover",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }

                Spacer(modifier = Modifier.padding(2.dp))

                Text(
                    text = "${data.quantity}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )

                Spacer(modifier = Modifier.padding(2.dp))

                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.AddCircleOutline,
                        contentDescription = "Adicionar",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "SUBTOTAL",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    textAlign = TextAlign.End
                )

                Text(
                    text = Format().formatCurrencyToBRL(data.quantity * data.price),
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