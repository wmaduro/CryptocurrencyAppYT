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
        collectFlowStrategyConflate()
//        collectFlowStrategyBuffer()
//        collectFlowStrategySequential()
//        flatOperator()
//        collectTest()
//        collectUsingOnEach()
    }
    private fun collectFlowStrategyConflate(){
        val restaurantFlow = flow {
            delay(500)
            emit("Appetizer")
            delay(1500)
            emit("Main Dish")
            delay(500)
            emit("Desert")
        }

        viewModelScope.launch {
            restaurantFlow.onEach {
                println("lixo - $it is delivered")
            }
                .conflate()
                .collect { dish ->
                    println("lixo - eating $dish")
                    delay(3000)
                    println("lixo - FINISH $dish")
                }
        }
    }

    private fun collectFlowStrategyBuffer(){
        val restaurantFlow = flow {
            delay(500)
            emit("Appetizer")
            delay(1500)
            emit("Main Dish")
            delay(500)
            emit("Desert")
        }

        viewModelScope.launch {
            restaurantFlow.onEach {
                println("lixo - $it is delivered")
            }
                .buffer()
                .collect { dish ->
                    println("lixo - eating $dish")
                    delay(3000)
                    println("lixo - FINISH $dish")
                }
        }
    }

    private fun collectFlowStrategySequential(){
        val restaurantFlow = flow {
            delay(500)
            emit("Appetizer")
            delay(1500)
            emit("Main Dish")
            delay(500)
            emit("Desert")
        }

        viewModelScope.launch {
            restaurantFlow.onEach {
                println("lixo - $it is delivered")
            }
                .collect { dish ->
                println("lixo - eating $dish")
                delay(3000)
                println("lixo - FINISH $dish")
            }
        }
    }

    private fun flatOperator() {

        val flow1 = flow {
            emit(1)
            delay(1000)
            emit(2)
        }

        viewModelScope.launch {
            flow1.flatMapConcat { value ->
                flow {
                    emit(value + 1)
                    delay(1000)
                    emit(value + 2)
                }
            }.collect { value ->
                println("lixo $value")
            }
        }
    }

    private fun collectTest() {
        viewModelScope.launch {
            val myReduce = countDownFlow
                .onEach { time ->
                    println("lixo $time")
                }
                .reduce { acummulator, value ->
                    acummulator + value
                }

            println("lixo myReduce = $myReduce")
        }

        viewModelScope.launch {
            val myFold = countDownFlow
                .onEach { time ->
                    println("lixo $time")
                }
                .fold(100) { acummulator, value ->
                    acummulator + value
                }

            println("lixo myFold = $myFold")
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

