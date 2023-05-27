package com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_coins.FakeHeaderUseCase
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_coins.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.event.CoinListEvent
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.state.CoinListState
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.state.FakeHeaderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val fakeHeaderUseCase: FakeHeaderUseCase
) : ViewModel() {

    private val _coinListState = mutableStateOf(CoinListState())
    val coinListState: State<CoinListState> = _coinListState

    private val _fakeHeaderState = mutableStateOf(FakeHeaderState())
    val fakeHeaderState: State<FakeHeaderState> = _fakeHeaderState

    init {
        getCoins()
        getLixo()
    }

    fun onEvent(event: CoinListEvent) {
        when (event) {
            is CoinListEvent.RefreshLixo -> {
                getLixo()
            }
            CoinListEvent.RefreshData -> {
                getCoins()
            }
        }
    }

    private fun getLixo() {
        viewModelScope.launch {
            fakeHeaderUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> _fakeHeaderState.value =
                        FakeHeaderState(error = "errooooo")
                    is Resource.Loading -> _fakeHeaderState.value =
                        FakeHeaderState(isLoading = true)
                    is Resource.Success -> _fakeHeaderState.value =
                        FakeHeaderState(data = result.data ?: "")
                }
            }
        }
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _coinListState.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _coinListState.value = CoinListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _coinListState.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}