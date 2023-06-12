package com.plcoding.cryptocurrencyappyt.feature.my_counter.prsesentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CounterScreen(
    navController: NavController,
    viewModel: CounterViewModel = hiltViewModel()
) {
    val timeState = viewModel.countDownFlow.collectAsState(initial = 1000)
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = timeState.value.toString(),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}