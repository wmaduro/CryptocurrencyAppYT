package com.plcoding.cryptocurrencyappyt.feature.my_counter.prsesentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
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

    init {
        collectTest()
    }
   private fun collectTest(){
        viewModelScope.launch {
            countDownFlow.collectLatest {  time ->
                println("lixo waiting $time")
                delay(3000)
                println("lixo $time")
            }
        }
    }
}
