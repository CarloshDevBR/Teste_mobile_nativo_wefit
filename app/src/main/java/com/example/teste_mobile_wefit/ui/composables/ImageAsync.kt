package com.example.teste_mobile_wefit.ui.composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.teste_mobile_wefit.R
import coil.compose.AsyncImage

@Composable
fun ImageAsync(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String = "",
) {
    AsyncImage(
        modifier = modifier
            .width(250.dp)
            .height(250.dp),
        model = url,
        contentDescription = contentDescription,
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        error = painterResource(R.drawable.ic_launcher_foreground),
    )
}
