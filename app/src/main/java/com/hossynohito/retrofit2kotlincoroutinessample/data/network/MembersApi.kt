package com.hossynohito.trello.data.network

import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Board
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MembersApi {

    @GET("members/{name}/boards")
    fun getBoards(
            @Path("name") userName: String,
            @Query("key") key: String,
            @Query("token") token: String
    ): Deferred<List<Board>>
}