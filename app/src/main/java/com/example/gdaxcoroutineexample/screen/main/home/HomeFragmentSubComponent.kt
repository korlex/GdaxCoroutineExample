package com.example.gdaxcoroutineexample.screen.main.home

import com.example.gdaxcoroutineexample.di.scopes.FragmentScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [HomeFragmentModule::class])
@FragmentScope
interface HomeFragmentSubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance homeFragment: HomeFragment): HomeFragmentSubComponent
    }

    fun inject(homeFragment: HomeFragment)
}