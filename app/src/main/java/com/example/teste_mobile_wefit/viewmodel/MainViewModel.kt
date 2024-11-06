package com.example.teste_mobile_wefit.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _isVisibleBottomBar = MutableStateFlow(false)
    val isVisibleBottomBar: StateFlow<Boolean> get() = _isVisibleBottomBar

    fun setIsVisibleBottomBar(value: Boolean) {
        _isVisibleBottomBar.update { value }
    }
}