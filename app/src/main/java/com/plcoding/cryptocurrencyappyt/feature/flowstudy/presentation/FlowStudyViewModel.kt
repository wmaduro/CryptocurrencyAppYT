package com.plcoding.cryptocurrencyappyt.feature.flowstudy.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowStudyViewModel @Inject constructor() : ViewModel() {

    //STATE
    private val _myMutableState = mutableStateOf("")
    val myState: State<String> = _myMutableState

    //STATE FLOW
    private val _myCounterMutableStateFlow = MutableStateFlow("")
    val myCounterStateFlow: StateFlow<String>  = _myCounterMutableStateFlow.asStateFlow()


    init {
        viewModelScope.launch {
            //STATE FLOW
//            myCounterStateFlow.collect {
//                println("maduro - myStateFlow  $it")
//            }
            _myCounterMutableStateFlow.collect {
                println("maduro - _myMutableStateFlow  $it")
            }

        }
    }

    fun startMyCounterFlow(){
        viewModelScope.launch {
            myCounterFlow().collect() {
                _myMutableState.value = it
                _myCounterMutableStateFlow.value = it
            }
        }
    }

    private fun myCounterFlow(): Flow<String> {
        return flow {
            repeat(5) {
                emit("$it")
                delay(1000)
            }
        }
    }
}