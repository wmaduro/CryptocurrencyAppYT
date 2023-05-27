package com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.state

import com.plcoding.cryptocurrencyappyt.feature.coin_list.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
