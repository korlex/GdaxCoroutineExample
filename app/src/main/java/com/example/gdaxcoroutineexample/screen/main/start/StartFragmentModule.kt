package com.example.gdaxcoroutineexample.screen.main.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.gdaxcoroutineexample.api.GdaxApi
import com.example.gdaxcoroutineexample.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class StartFragmentModule {

    @Provides
    @FragmentScope
    fun provideStartViewModel(startFragment: StartFragment, vmFactory: ViewModelProvider.Factory): StartViewModel {
        return ViewModelProviders.of(startFragment, vmFactory).get(StartViewModel::class.java)
    }

    @Provides
    @FragmentScope
    fun provideStartViewModelFactory(gdaxApi: GdaxApi): ViewModelProvider.Factory {
        return StartViewModel.Factory(gdaxApi)
    }
}