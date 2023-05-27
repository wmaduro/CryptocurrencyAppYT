package com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.state

import com.plcoding.cryptocurrencyappyt.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
