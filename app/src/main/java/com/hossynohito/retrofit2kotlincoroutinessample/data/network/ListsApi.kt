package com.hossynohito.retrofit2kotlincoroutinessample.data.network

import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Card
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListsApi {

    @GET("lists/{id}/cards")
    fun getCards(
            @Path("id") pipelineId: String,
            @Query("key") key: String,
            @Query("token") token: String
    ): Deferred<List<Card>>
}