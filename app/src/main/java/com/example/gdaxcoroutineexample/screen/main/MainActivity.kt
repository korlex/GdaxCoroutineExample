package com.example.gdaxcoroutineexample.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gdaxcoroutineexample.App
import com.example.gdaxcoroutineexample.R
import com.example.gdaxcoroutineexample.api.GdaxApi
import com.neovisionaries.ws.client.WebSocketState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var gdaxApi: GdaxApi
    lateinit var mainActivitySubComponent: MainActivitySubComponent

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        initSubComponent()
        mainActivitySubComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        listenWsState()
        listenWsError()
    }

    private fun initSubComponent() {
        mainActivitySubComponent = App
            .get()
            .appComponent
            .mainActivitySubComponent()
            .create(this)
    }

    private fun listenWsState() {
        MainScope().launch {
            gdaxApi.wsStateFlow.collect { state ->
                when(state) {
                    WebSocketState.CLOSED -> navController.popBackStack(R.id.startFragment, true)
                    WebSocketState.OPEN   -> navController.navigate(R.id.mainFragment)
                }
            }
        }
    }

    private fun listenWsError() {
        MainScope().launch {
            gdaxApi.wsErrorFlow.collect { wsException ->
                val errorText = wsException.message ?: "Unknown error"
                Toast.makeText(this@MainActivity, errorText, Toast.LENGTH_SHORT).show()
            }
        }
    }
}