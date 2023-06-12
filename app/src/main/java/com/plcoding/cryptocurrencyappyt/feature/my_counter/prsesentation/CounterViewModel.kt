package com.plcoding.cryptocurrencyappyt.feature.my_counter.prsesentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CounterViewModel  @Inject constructor(): ViewModel() {
    val countDownFlow = flow <Int>{
        val startValue = 10
        var currValue = startValue
        emit(startValue)

        while (currValue >0 ){
            delay(1_000)
            currValue--
            emit(currValue)
        }
    }
}
