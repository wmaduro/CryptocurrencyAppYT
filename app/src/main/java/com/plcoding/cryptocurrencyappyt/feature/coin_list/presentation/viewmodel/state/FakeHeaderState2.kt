package com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.viewmodel.state

import com.plcoding.cryptocurrencyappyt.shared.common.Resource

sealed interface FakeHeaderState2 {
    object Loading : FakeHeaderState2

    data class Success(
        val fakeHeader: Resource<String>
    ) : FakeHeaderState2

    object Error : FakeHeaderState2
}