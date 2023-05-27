package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.presentation.Screen
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.components.coinlist.CoinList
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.components.headerbuttons.HeaderButtons
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.components.lixoheader.LixoHeader
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.event.CoinListEvent
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.CoinListViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val coinListState = viewModel.coinListState.value
    val lixoState = viewModel.lixoHeaderState.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderButtons(
            onLixoClick = {
                viewModel.onEvent(CoinListEvent.RefreshLixo)
            },
            onDataClick = {
                viewModel.onEvent(CoinListEvent.RefreshData)
            }
        )

        LixoHeader(
            data = lixoState.data,
            isLoading = lixoState.isLoading
        )

        CoinList(
            coins = coinListState.coins,
            onItemClick = { coinId ->
                navigateToItemDetail(coinId, navController)
            },
            error = coinListState.error,
            isLoading = coinListState.isLoading
        )

    }
}

private fun navigateToItemDetail(coinId: String, navController: NavController) {
    navController.navigate(Screen.CoinDetailScreen.route + "/${coinId}")
}