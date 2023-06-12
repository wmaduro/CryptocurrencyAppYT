package com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.components.coinlist.CoinList
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.components.fakeheader.FakeHeader
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.components.headerbuttons.HeaderButtons
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.CoinListViewModel
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.event.CoinListEvent
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.state.FakeHeaderState2
import com.plcoding.cryptocurrencyappyt.shared.navigation.Screen

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val coinListState = viewModel.coinListState.value
    val fakeHeaderState = viewModel.fakeHeaderState.value
    val fakeHeaderState2  by viewModel.fakeHeaderStateFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HeaderButtons(
            onFakeHeaderClick = {
                viewModel.onEvent(CoinListEvent.RefreshFakeHeader)
            },
            isFakeHeaderEnabled = fakeHeaderState.isLoading.not(),
            onDataClick = {
                viewModel.onEvent(CoinListEvent.RefreshData)
            },
            isDataButtonEnabled = coinListState.isLoading.not()
        )

          HeaderButtons(
            onFakeHeaderClick = {
                viewModel.onEvent(CoinListEvent.RefreshFakeHeader)
            },
            isFakeHeaderEnabled = fakeHeaderState2 != FakeHeaderState2.Loading.also{ it ->
                println("maduro $it")
            },
            onDataClick = {
                viewModel.onEvent(CoinListEvent.RefreshData)
            },
            isDataButtonEnabled = fakeHeaderState2 != FakeHeaderState2.Loading,
        )

        FakeHeader(
            data = fakeHeaderState.data,
            isLoading = fakeHeaderState.isLoading
        )

        when(fakeHeaderState2){
            FakeHeaderState2.Error -> {}
            FakeHeaderState2.Loading -> {}
            is FakeHeaderState2.Success -> {
                FakeHeader(
                    data = (fakeHeaderState2 as FakeHeaderState2.Success).fakeHeader.data.toString(),
                    isLoading = fakeHeaderState.isLoading
                )
            }
        }



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