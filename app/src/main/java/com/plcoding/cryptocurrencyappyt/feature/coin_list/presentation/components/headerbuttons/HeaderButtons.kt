package com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.components.headerbuttons

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HeaderButtons(
    onFakeHeaderClick: () -> Unit,
    onDataClick: () -> Unit,
    isFakeHeaderEnabled: Boolean,
    isDataButtonEnabled: Boolean
) {
    Row(){
        Button(onClick = onFakeHeaderClick, enabled = isFakeHeaderEnabled) {
            Text(text = "FakeHeader")
        }
        Button(onClick = onDataClick, enabled = isDataButtonEnabled) {
            Text(text = "Data")
        }
    }
}