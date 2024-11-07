package com.example.teste_mobile_wefit.ui.screen.home.composables

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.teste_mobile_wefit.model.http.ProductModel
import com.example.teste_mobile_wefit.ui.composables.ImageAsync
import com.example.teste_mobile_wefit.utils.Format
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CardMovie(
    isAddedCart: Boolean,
    quantity: Int?,
    data: ProductModel,
    onClick: (data: ProductModel) -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.onTertiary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageAsync(
            modifier = Modifier
                .width(150.dp)
                .height(190.dp),
            url = data.image,
            contentDescription = data.title
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = data.title,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontFamily = MaterialTheme.typography.bodySmall.fontFamily
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = Format().formatCurrencyToBRL(data.price),
            color = MaterialTheme.colorScheme.surface,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            onClick = {
                coroutineScope.launch {
                    isLoading = true

                    onClick(data)

                    isLoading = false
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isAddedCart) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                disabledContainerColor = MaterialTheme.colorScheme.onSecondary
            ),
            shape = RoundedCornerShape(4.dp),
            enabled = !isLoading
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Outlined.AddShoppingCart,
                    contentDescription = "add ${data.title}",
                    tint = MaterialTheme.colorScheme.onTertiary
                )

                quantity?.let {
                    Spacer(modifier = Modifier.padding(2.dp))

                    Text(
                        text = it.toString(),
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily
                    )
                }
            }

            Spacer(modifier = Modifier.padding(6.dp))

            Text(
                text = "Adicionar ao carrinho".uppercase(),
                color = MaterialTheme.colorScheme.onTertiary,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily
            )
        }
    }
}