package com.plcoding.cryptocurrencyappyt.feature.effecthandlers.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LaunchedEffectFlowDemo(
    viewModel: LaunchedEffectViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    val screenEvent = viewModel.sharedFlow.collectAsState(LaunchedEffectViewModel.ScreenEvents.NONE)

    LaunchedEffect(key1 = screenEvent.value) {
//        viewModel.sharedFlow.collect() { event ->
//        val showSnackBar = event.value as LaunchedEffectViewModel.ScreenEvents.ShowSnackBar

        when (screenEvent.value) {
            is LaunchedEffectViewModel.ScreenEvents.ShowSnackBar -> {
                println("maduro EVENT - > ${(screenEvent.value as LaunchedEffectViewModel.ScreenEvents.ShowSnackBar).message}")
                scaffoldState.snackbarHostState.showSnackbar(
                    message = ((screenEvent.value as LaunchedEffectViewModel.ScreenEvents.ShowSnackBar).message),
                    duration = SnackbarDuration.Short,
                )
            }
            else -> {
                println("maduro else ${screenEvent.value}")
            }
        }
//        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Button(onClick = {
            viewModel.emitSnackBar()
        }) {
            Text(text = "emit snackbar")
        }
    }
}