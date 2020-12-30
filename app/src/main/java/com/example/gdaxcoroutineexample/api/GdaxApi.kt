package com.example.gdaxcoroutineexample.api

import android.util.Log
import com.neovisionaries.ws.client.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import java.net.URI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GdaxApi @Inject constructor() : WebSocketAdapter() {

    var webSocket: WebSocket? = null

    val wsStateFlow: MutableSharedFlow<WebSocketState> = MutableSharedFlow<WebSocketState>()
    val wsMessageFlow: MutableSharedFlow<String> = MutableSharedFlow<String>()
    val wsErrorFlow: MutableSharedFlow<WebSocketException> = MutableSharedFlow<WebSocketException>()


    override fun onStateChanged(websocket: WebSocket?, newState: WebSocketState?) {
        runBlocking {
            Log.d("TAG", "thread = ${Thread.currentThread()}")
            withContext(Dispatchers.Main) {
                Log.d("TAG", "thread = ${Thread.currentThread()}")
                newState?.let { wsStateFlow.emit(it) }
            }
        }
    }

    override fun onError(websocket: WebSocket?, exception: WebSocketException?) {
        runBlocking {
            withContext(Dispatchers.Main) {
                exception?.let { wsErrorFlow.emit(it) }
            }
        }
    }

    override fun onTextMessage(websocket: WebSocket?, message: String?) {
        runBlocking {
            withContext(Dispatchers.Main) {
                message?.let { wsMessageFlow.emit(it) }
            }
        }
    }

    fun sendTextMessage(json: String) {
        Log.d("TAG", "sending msg = $json to  ws = $webSocket")
        webSocket?.sendText(json)
    }


    fun createWebSocket() {
        val factory = WebSocketFactory().apply {
//            if(isSecure) {
//                sslContext = NativeSSLContext.getSSLContext("TLSv1.2")
//                verifyHostname = false
//            }

            sslContext = NativeSSLContext.getSSLContext("TLSv1.2")
        }

        webSocket = factory.setConnectionTimeout(3000)
            .setVerifyHostname(false)
            .createSocket("wss://ws-feed.gdax.com")
            .setMissingCloseFrameAllowed(false)
            .addListener(this)

        webSocket?.connectAsynchronously()
    }
}