package com.example.teste_mobile_wefit.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teste_mobile_wefit.R
import com.example.teste_mobile_wefit.service.api.NetworkResponse
import com.example.teste_mobile_wefit.ui.composables.EmptyScreen
import com.example.teste_mobile_wefit.ui.composables.GradientCircularLoading
import com.example.teste_mobile_wefit.ui.composables.Header
import com.example.teste_mobile_wefit.ui.screen.home.composables.CardMovie
import com.example.teste_mobile_wefit.viewmodel.HomeViewModel
import com.example.teste_mobile_wefit.viewmodel.MainViewModel

@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    val viewModel = viewModel(HomeViewModel::class.java)

    val movies = viewModel.movies.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMovies()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        when (val result = movies.value) {
            is NetworkResponse.Success -> {
                mainViewModel.setIsVisibleBottomBar(true)

                LazyColumn(modifier = Modifier.padding(horizontal = 24.dp)) {
                    item {
                        Header(
                            title = "Mais vendidos",
                            subTitle = "Maiores sucessos do WeMovies"
                        )
                    }

                    items(result.data.products.size) { index ->
                        val data = result.data.products[index]

                        CardMovie(data = data) { viewModel.addItemCart(it) }

                        Spacer(modifier = Modifier.padding(bottom = 24.dp))
                    }
                }
            }

            NetworkResponse.Loading -> {
                mainViewModel.setIsVisibleBottomBar(false)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GradientCircularLoading()
                }
            }

            else -> {
                mainViewModel.setIsVisibleBottomBar(false)

                EmptyScreen(
                    modifier = Modifier.padding(24.dp),
                    title = "Parece que não há nada por aqui :(",
                    drawableResId = R.drawable.retry,
                    textButton = "Recarregar página",
                    actionButton = { viewModel.getMovies() }
                )
            }
        }
    }
}