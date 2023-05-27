package com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.event

sealed class CoinListEvent {
    object RefreshLixo: CoinListEvent()
    object RefreshData: CoinListEvent()
}