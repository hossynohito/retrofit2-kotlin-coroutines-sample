package com.hossynohito.retrofit2kotlincoroutinessample

import com.hossynohito.retrofit2kotlincoroutinessample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder().create(this)
}
