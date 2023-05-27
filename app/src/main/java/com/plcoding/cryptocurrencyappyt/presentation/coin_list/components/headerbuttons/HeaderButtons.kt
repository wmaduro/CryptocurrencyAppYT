package com.plcoding.cryptocurrencyappyt.presentation.coin_list.components.headerbuttons

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HeaderButtons(
    onLixoClick: () -> Unit,
    onDataClick: () -> Unit
) {
    Row(){
        Button(onClick = onLixoClick) {
            Text(text = "Lixo")
        }
        Button(onClick = onDataClick) {
            Text(text = "Data")
        }
    }
}