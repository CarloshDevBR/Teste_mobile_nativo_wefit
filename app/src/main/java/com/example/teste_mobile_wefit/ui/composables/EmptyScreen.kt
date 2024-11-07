package com.example.teste_mobile_wefit.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    imageModifier: Modifier,
    title: String,
    subtitle: AnnotatedString? = null,
    drawableResId: Int,
    textButton: String,
    actionButton: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onTertiary)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        subtitle?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                textAlign = TextAlign.Center
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp)
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.background,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
                textAlign = TextAlign.Center,
            )
        }

        Image(
            modifier = imageModifier,
            painter = painterResource(id = drawableResId),
            contentDescription = ""
        )

        Button(
            modifier = Modifier.width(174.dp).height(40.dp),
            onClick = { actionButton() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = textButton,
                color = MaterialTheme.colorScheme.onTertiary,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily
            )
        }
    }
}