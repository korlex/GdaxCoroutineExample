package com.example.gdaxcoroutineexample.di.scopes

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Scope
@Retention(RUNTIME)
internal annotation class ActivityScope