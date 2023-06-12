package com.plcoding.cryptocurrencyappyt.feature.my_counter.prsesentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor() : ViewModel() {


    val countDownFlow = flow<Int> {
        val startValue = 10
        var currValue = startValue
        emit(startValue)

        while (currValue > 0) {
            delay(1_000)
            currValue--
            emit(currValue)
        }
    }

    init {
        collectTest()
//        collectUsingOnEach()
    }


    private fun collectTest() {
        viewModelScope.launch {
            val count = countDownFlow
//                .filter { time ->
//                    time > 3
//                }
//                .map { time ->
//                    time * time
//                }
                .onEach { time ->
                    println("lixo $time")
                }
                .count{ value ->
                    value % 2 == 0
                }

            println ("lixo count = $count")
        }
    }

    private fun collectUsingOnEach() {
        countDownFlow
            .onEach { time ->
                println("lixo oneach $time")
            }
            .launchIn(viewModelScope)

    }
}

