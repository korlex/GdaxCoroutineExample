package com.example.gdaxcoroutineexample.di

import com.example.gdaxcoroutineexample.App
import com.example.gdaxcoroutineexample.screen.main.MainActivitySubComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubComponentsModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }

    fun mainActivitySubComponent(): MainActivitySubComponent.Factory
    fun inject(app: App)
}