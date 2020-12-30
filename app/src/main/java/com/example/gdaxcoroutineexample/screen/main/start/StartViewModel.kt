package com.example.gdaxcoroutineexample.screen.main.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gdaxcoroutineexample.api.GdaxApi
import com.neovisionaries.ws.client.WebSocketState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StartViewModel(private val gdaxApi: GdaxApi) : ViewModel() {

    val connectionState: MutableLiveData<WebSocketState> = MutableLiveData()

    init { listenWsState() }

    fun connect() { gdaxApi.createWebSocket() }

    private fun listenWsState() {
        viewModelScope.launch {
            gdaxApi.wsStateFlow.collect {
                connectionState.value = it
            }
        }
    }

//    private fun listenWsMessages() {
//        viewModelScope.launch {
//            gdaxApi.wsMessageFlow.collect {
//                Log.d("TAG", "message = $it")
//            }
//        }
//    }
//
//    private fun listenWsErrors() {
//        viewModelScope.launch {
//            gdaxApi.wsErrorFlow.collect {
//                Log.d("TAG", "error = ${it.message}")
//            }
//        }
//    }
//
//    fun subscribe() {
//        Log.d("TAG", "subscribe() called")
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                gdaxApi.sendTextMessage(subscribeMessage)
//            }
//        }
//    }

    class Factory(private val gdaxApi: GdaxApi) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StartViewModel(gdaxApi) as T
        }
    }
}