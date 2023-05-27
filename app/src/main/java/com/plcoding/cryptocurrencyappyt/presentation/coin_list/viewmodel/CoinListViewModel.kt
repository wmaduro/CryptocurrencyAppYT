package com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_coins.LixoHeaderUseCase
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_coins.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.event.CoinListEvent
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.state.CoinListState
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.viewmodel.state.LixoHeaderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val lixoHeaderUseCase: LixoHeaderUseCase
) : ViewModel() {

    private val _coinListState = mutableStateOf(CoinListState())
    val coinListState: State<CoinListState> = _coinListState

    private val _lixoHeaderState = mutableStateOf(LixoHeaderState())
    val lixoHeaderState: State<LixoHeaderState> = _lixoHeaderState

    init {
        getCoins()
        getLixo()
    }

    fun onEvent(event: CoinListEvent) {
        when(event) {
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
            lixoHeaderUseCase().collect { result ->
                when (result) {
                    is Resource.Error -> _lixoHeaderState.value = LixoHeaderState(error = "errooooo")
                    is Resource.Loading -> _lixoHeaderState.value = LixoHeaderState(isLoading = true)
                    is Resource.Success -> _lixoHeaderState.value = LixoHeaderState(data = result.data ?: "")
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