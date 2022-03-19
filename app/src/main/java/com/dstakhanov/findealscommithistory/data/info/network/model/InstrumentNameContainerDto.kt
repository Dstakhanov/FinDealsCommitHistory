package com.dstakhanov.findealscommithistory.data.info.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InstrumentNameContainerDto(
    @SerializedName("CoinInfo")
    @Expose
    val instrumentName: InstrumentNameDto? = null
)