package com.plcoding.cryptocurrencyappyt.feature.flowstudy.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import java.util.Date

@Composable
fun FlowStudyScreen(
    viewModel: FlowStudyViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.myCounterStateFlow.collect() {
            if (it.isNotBlank()) {
                println("maduro - LaunchedEffect myCounterStateFlow - $it")
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.myCounterFlow().collect() {
            println("maduro - LaunchedEffect myCounterFlow - $it")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row() {
            Text(
                text = "State: ",
                color = Color.Black
            )
            Text(
                text = viewModel.myState.value,
                color = Color.Black
            )
        }
        Row() {
            Text(
                text = "StateFlow: ",
                color = Color.Black
            )
            Text(
                text = viewModel.myCounterStateFlow.value,
                color = Color.Black
            )
        }
        Divider(Modifier.height(20.dp))
        //FLOW
        BuildBlock("Flow") {
            viewModel.startMyCounterFlow()
        }

        //STATE FLOW
        BuildBlock(viewModel.myStateFlow.collectAsState().value) {
            viewModel.refreshMyStateFlow("start state flow ${Date().time}")
        }
        Divider(Modifier.height(10.dp))

        //SHARED FLOW
        Divider(Modifier.height(10.dp))
        BuildBlock(viewModel.myStateSharedFlow.collectAsState(initial = "shared flow init").value) {
            viewModel.refreshMyStateSharedFlow("shared flow ${Date().time}")
        }

    }
}

@Composable
private fun BuildBlock(text: String, buttonClick: () -> Unit) {
    Text(
        text,
        color = Color.Black
    )
    Button(onClick = buttonClick) {
        Text(
            text = text,
        )
    }
}

//fun buildItem(): List {
//    TODO("Not yet implemented")
//}
