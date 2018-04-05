package com.hossynohito.retrofit2kotlincoroutinessample.domain.entity

import java.io.Serializable

data class Board(
        val id: String,
        val name: String
) : Serializable