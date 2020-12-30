package com.example.gdaxcoroutineexample.screen.main

import com.example.gdaxcoroutineexample.screen.main.home.HomeFragmentSubComponent
import com.example.gdaxcoroutineexample.screen.main.start.StartFragmentSubComponent
import dagger.Module

@Module(subcomponents = [StartFragmentSubComponent::class, HomeFragmentSubComponent::class])
interface MainSubComponentModule