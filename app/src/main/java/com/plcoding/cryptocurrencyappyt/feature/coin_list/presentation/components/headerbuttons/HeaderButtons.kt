package com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.components.headerbuttons

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HeaderButtons(
    onLixoClick: () -> Unit,
    onDataClick: () -> Unit,
    isLixoEnabled: Boolean,
    isDataButtonEnabled: Boolean
) {
    Row(){
        Button(onClick = onLixoClick, enabled = isLixoEnabled) {
            Text(text = "Lixo")
        }
        Button(onClick = onDataClick, enabled = isDataButtonEnabled) {
            Text(text = "Data")
        }
    }
}