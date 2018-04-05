package com.hossynohito.retrofit2kotlincoroutinessample.domain.entity

import java.io.Serializable

data class Pipeline(
        val id: String,
        val name: String
) : Serializable