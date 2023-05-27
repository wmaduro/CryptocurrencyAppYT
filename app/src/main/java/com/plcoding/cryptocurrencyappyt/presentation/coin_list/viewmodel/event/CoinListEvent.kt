package com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.event

sealed class CoinListEvent {
    object RefreshLixo: CoinListEvent()
    object RefreshData: CoinListEvent()
}