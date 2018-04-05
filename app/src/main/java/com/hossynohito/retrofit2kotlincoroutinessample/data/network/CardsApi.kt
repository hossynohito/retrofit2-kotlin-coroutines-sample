package com.hossynohito.retrofit2kotlincoroutinessample.data.network

import com.hossynohito.retrofit2kotlincoroutinessample.domain.entity.Card
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.*

interface CardsApi {

    @FormUrlEncoded
    @POST("cards")
    fun addCard(
            @Field("idList") pipelineId: String,
            @Field("name") cardName: String,
            @Field("key") key: String,
            @Field("token") token: String
    ): Deferred<Card>

    @DELETE("cards/{id}")
    fun deleteCard(
            @Path("id") cardId: String,
            @Query("key") key: String,
            @Query("token") token: String
    ): Deferred<Unit>
}