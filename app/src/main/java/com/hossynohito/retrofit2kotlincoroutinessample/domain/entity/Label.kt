package com.hossynohito.retrofit2kotlincoroutinessample.domain.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Label(
        val id: String,
        val name: String,
        val color: Color
) : Serializable {

    enum class Color {
        @SerializedName("yellow")
        YELLOW,
        @SerializedName("purple")
        PURPLE,
        @SerializedName("blue")
        BLUE,
        @SerializedName("red")
        RED,
        @SerializedName("green")
        GREEN,
        @SerializedName("orange")
        ORANGE,
        @SerializedName("black")
        BLACK,
        @SerializedName("sky")
        SKY,
        @SerializedName("pink")
        PINK,
        @SerializedName("lime")
        LIME,
        @SerializedName("null")
        NULL
    }
}
