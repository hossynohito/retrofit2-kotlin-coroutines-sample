package com.hossynohito.retrofit2kotlincoroutinessample.di

import com.hossynohito.retrofit2kotlincoroutinessample.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ActivityModule::class
])
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

    fun retrofit(): Retrofit

    fun okHttpClient(): OkHttpClient
}
