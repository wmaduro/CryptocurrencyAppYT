package com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.feature.coin_list.domain.use_case.FakeHeaderUseCase
import com.plcoding.cryptocurrencyappyt.feature.coin_list.domain.use_case.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.event.CoinListEvent
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.state.CoinListState
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.state.FakeHeaderState
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.state.FakeHeaderState2
import com.plcoding.cryptocurrencyappyt.shared.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    val fakeHeaderStateFlow: StateFlow<FakeHeaderState2> =
        fakeHeaderUseCase().map(FakeHeaderState2::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FakeHeaderState2.Loading
        )

    init {
        getCoins()
        getFakeHeader()
    }

    fun onEvent(event: CoinListEvent) {
        when (event) {
            is CoinListEvent.RefreshFakeHeader -> {
                getFakeHeader()
            }
            CoinListEvent.RefreshData -> {
                getCoins()
            }
        }
    }

    private fun getFakeHeader() {
        viewModelScope.launch {
            fakeHeaderUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> _fakeHeaderState.value =
                        FakeHeaderState(data = result.data ?: "")
                    is Resource.Error -> _fakeHeaderState.value =
                        FakeHeaderState(error = "errooooo")
                    is Resource.Loading -> _fakeHeaderState.value =
                        FakeHeaderState(isLoading = true)

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