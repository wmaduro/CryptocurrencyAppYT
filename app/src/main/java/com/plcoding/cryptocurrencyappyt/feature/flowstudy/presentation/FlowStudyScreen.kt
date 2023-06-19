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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@Composable
fun FlowStudyScreen(
    viewModel: FlowStudyViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        delay(2000)
        viewModel.myCounterStateFlow.collect() {
            println("maduro - LaunchedEffect - $it")
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
        BuildBlock("State Flow") {}
        Divider(Modifier.height(10.dp))

        //SHARED FLOW
        Divider(Modifier.height(10.dp))
        BuildBlock("Shared Flow") {}

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
