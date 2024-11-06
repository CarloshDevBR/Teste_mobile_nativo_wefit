package com.example.teste_mobile_wefit.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Header(title: String, subTitle: String? = null) {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onTertiary,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
        )

        subTitle?.let {
            Spacer(modifier = Modifier.padding(2.dp))

            Text(
                text = subTitle,
                color = MaterialTheme.colorScheme.onTertiary,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily
            )
        }
    }
}