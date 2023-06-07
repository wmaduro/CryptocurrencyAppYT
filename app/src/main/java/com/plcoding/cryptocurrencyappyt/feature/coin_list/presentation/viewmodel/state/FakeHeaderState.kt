package com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.state

data class FakeHeaderState(
    val isLoading: Boolean = false,
    val data: String = "",
    val error: String = ""
)