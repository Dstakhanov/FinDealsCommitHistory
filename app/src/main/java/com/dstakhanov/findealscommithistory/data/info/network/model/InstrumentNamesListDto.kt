package com.dstakhanov.findealscommithistory.data.info.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InstrumentNamesListDto(
    @SerializedName("Data")
    @Expose
    val names: List<InstrumentNameContainerDto>? = null
)