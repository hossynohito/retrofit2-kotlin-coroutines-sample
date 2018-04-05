package com.hossynohito.retrofit2kotlincoroutinessample.data.network

import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Pipeline
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardsApi {

    @GET("boards/{id}/lists")
    fun getPipelines(
            @Path("id") boardId: String,
            @Query("key") key: String,
            @Query("token") token: String
    ): Deferred<List<Pipeline>>
}