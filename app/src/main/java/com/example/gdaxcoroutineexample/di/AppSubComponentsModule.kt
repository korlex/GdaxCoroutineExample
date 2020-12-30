package com.example.gdaxcoroutineexample.di

import com.example.gdaxcoroutineexample.screen.main.MainActivitySubComponent
import dagger.Module

@Module(subcomponents = [MainActivitySubComponent::class])
interface AppSubComponentsModule