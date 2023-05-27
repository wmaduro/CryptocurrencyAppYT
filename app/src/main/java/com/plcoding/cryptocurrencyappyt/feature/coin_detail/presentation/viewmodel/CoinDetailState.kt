package com.plcoding.cryptocurrencyappyt.feature.coin_detail.presentation.viewmodel

import com.plcoding.cryptocurrencyappyt.feature.coin_detail.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
