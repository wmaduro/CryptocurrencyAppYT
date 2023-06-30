package com.plcoding.cryptocurrencyappyt.feature.effecthandlers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class LaunchedEffectViewModel @Inject constructor() : ViewModel() {
    private val _sharedFlow = MutableSharedFlow<ScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun emitSnackBar() {
        viewModelScope.launch {
            val s = "hi ${Date().time}"
            println("maduro emit $s")
            _sharedFlow.emit(ScreenEvents.ShowSnackBar(s))
        }
    }

    sealed class ScreenEvents {
        data class ShowSnackBar(val message: String) : ScreenEvents()
        data class Navigate(val route: String) : ScreenEvents()
        object NONE : ScreenEvents()
    }
}