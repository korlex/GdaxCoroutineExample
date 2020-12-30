package com.example.gdaxcoroutineexample.screen.main.start

import com.example.gdaxcoroutineexample.di.scopes.FragmentScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [StartFragmentModule::class])
@FragmentScope
interface StartFragmentSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance startFragment: StartFragment): StartFragmentSubComponent
    }

    fun inject(startFragment: StartFragment)
}