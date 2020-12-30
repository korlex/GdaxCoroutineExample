package com.example.gdaxcoroutineexample.screen.main.home

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.gdaxcoroutineexample.api.GdaxApi
import com.example.gdaxcoroutineexample.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {

    @Provides
    @FragmentScope
    fun provideStartViewModel(homeFragment: HomeFragment, vmFactory: ViewModelProvider.Factory): HomeViewModel {
        return ViewModelProviders.of(homeFragment, vmFactory).get(HomeViewModel::class.java)
    }

    @Provides
    @FragmentScope
    fun provideStartViewModelFactory(gdaxApi: GdaxApi): ViewModelProvider.Factory {
        return HomeViewModel.Factory(gdaxApi)
    }
}