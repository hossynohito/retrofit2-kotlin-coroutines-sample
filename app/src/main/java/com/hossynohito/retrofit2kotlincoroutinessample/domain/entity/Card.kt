package com.hossynohito.retrofit2kotlincoroutinessample.domain.entity

import java.io.Serializable

data class Card(
        val id: String,
        val name: String,
        val labels: List<Label>
) : Serializable