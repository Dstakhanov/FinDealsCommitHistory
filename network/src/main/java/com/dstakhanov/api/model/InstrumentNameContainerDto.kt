package com.dstakhanov.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InstrumentNameContainerDto(
    @SerializedName("CoinInfo")
    @Expose
    val instrumentName: InstrumentNameDto? = null
)