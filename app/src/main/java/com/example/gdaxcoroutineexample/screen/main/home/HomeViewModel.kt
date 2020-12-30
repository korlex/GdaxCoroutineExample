package com.example.gdaxcoroutineexample.screen.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gdaxcoroutineexample.api.GdaxApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val gdaxApi: GdaxApi) : ViewModel() {

    val btcUsdValue: MutableLiveData<String> = MutableLiveData()
    val subscribeMessage = "{\"type\":\"subscribe\",\"product_ids\":[\"BTC-USD\"],\"channels\":[\"ticker\"]}"
    val unsubscribeMessage = "{\"type\":\"unsubscribe\",\"product_ids\":[\"BTC-USD\"],\"channels\":[\"ticker\"]}"

    init {
        listenWsMessages()
        subscribeOnBtcUsd()
    }

    fun disconnect() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                gdaxApi.sendTextMessage(unsubscribeMessage)
                gdaxApi.webSocket?.sendClose()
            }
        }
    }

    private fun listenWsMessages() {
        viewModelScope.launch {
            gdaxApi.wsMessageFlow.collect {
                delay(1000)
                btcUsdValue.value = it
            }
        }
    }

    private fun subscribeOnBtcUsd() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                gdaxApi.sendTextMessage(subscribeMessage)
            }
        }
    }


    class Factory(private val gdaxApi: GdaxApi) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(gdaxApi) as T
        }
    }
}