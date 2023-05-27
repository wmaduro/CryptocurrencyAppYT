package com.plcoding.cryptocurrencyappyt.feature.coin_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.shared.navigation.Screen
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.components.coinlist.CoinList
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.components.headerbuttons.HeaderButtons
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.components.fakeheader.LixoHeader
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.event.CoinListEvent
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.CoinListViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val coinListState = viewModel.coinListState.value
    val fakeHeaderState = viewModel.fakeHeaderState.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderButtons(
            onLixoClick = {
                viewModel.onEvent(CoinListEvent.RefreshLixo)
            },
            isLixoEnabled = fakeHeaderState.isLoading.not(),
            onDataClick = {
                viewModel.onEvent(CoinListEvent.RefreshData)
            },
            isDataButtonEnabled = coinListState.isLoading.not()
        )

        LixoHeader(
            data = fakeHeaderState.data,
            isLoading = fakeHeaderState.isLoading
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