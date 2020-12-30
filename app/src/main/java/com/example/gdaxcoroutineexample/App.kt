package com.example.gdaxcoroutineexample

import android.app.Application
import com.example.gdaxcoroutineexample.di.AppComponent
import com.example.gdaxcoroutineexample.di.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        app = this
        AndroidThreeTen.init(this)
        initAppComponent()
        appComponent.inject(this)
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
    }

    companion object {
        private lateinit var app: App
        fun get(): App = app
    }
}