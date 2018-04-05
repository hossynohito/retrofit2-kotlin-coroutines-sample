package com.hossynohito.retrofit2kotlincoroutinessample.di

import com.hossynohito.retrofit2kotlincoroutinessample.presentation.boarddetail.BoardDetailActivity
import com.hossynohito.retrofit2kotlincoroutinessample.presentation.boardlist.BoardListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun contributeBoardListActivity(): BoardListActivity

    @ContributesAndroidInjector(modules = [(BoardDetailActivity.Module::class), (BoardDetailActivity.FragmentModule::class)])
    fun contributeBoardDetailActivity(): BoardDetailActivity
}
