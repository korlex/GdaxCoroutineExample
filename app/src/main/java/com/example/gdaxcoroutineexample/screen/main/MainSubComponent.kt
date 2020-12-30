package com.example.gdaxcoroutineexample.screen.main

import com.example.gdaxcoroutineexample.di.scopes.ActivityScope
import com.example.gdaxcoroutineexample.screen.main.home.HomeFragmentSubComponent
import com.example.gdaxcoroutineexample.screen.main.start.StartFragmentSubComponent
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
@ActivityScope
interface MainActivitySubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance mainActivity: MainActivity): MainActivitySubComponent
    }

    fun startFragmentSubComponent(): StartFragmentSubComponent.Factory
    fun homeFragmentSubComponent(): HomeFragmentSubComponent.Factory
    fun inject(mainActivity: MainActivity)
}