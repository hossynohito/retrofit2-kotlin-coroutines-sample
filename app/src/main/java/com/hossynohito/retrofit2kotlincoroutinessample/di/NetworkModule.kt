package com.hossynohito.retrofit2kotlincoroutinessample.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hossynohito.retrofit2kotlincoroutinessample.BuildConfig
import com.hossynohito.retrofit2kotlincoroutinessample.data.network.BoardsApi
import com.hossynohito.retrofit2kotlincoroutinessample.data.network.CardsApi
import com.hossynohito.retrofit2kotlincoroutinessample.data.network.ListsApi
import com.hossynohito.trello.data.network.MembersApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .apply {
                        if (BuildConfig.DEBUG) {
                            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        }
                    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(client)
                    .build()

    @Provides
    @Singleton
    fun provideMembersApi(retrofit: Retrofit): MembersApi = retrofit.create(MembersApi::class.java)

    @Provides
    @Singleton
    fun provideBoardsApi(retrofit: Retrofit): BoardsApi = retrofit.create(BoardsApi::class.java)

    @Provides
    @Singleton
    fun provideListsApi(retrofit: Retrofit): ListsApi = retrofit.create(ListsApi::class.java)

    @Provides
    @Singleton
    fun provideCardsApi(retrofit: Retrofit): CardsApi = retrofit.create(CardsApi::class.java)

    companion object {
        const val API_KEY = BuildConfig.API_KEY
        const val ACCESS_TOKEN = BuildConfig.ACCESS_TOKEN
    }
}
