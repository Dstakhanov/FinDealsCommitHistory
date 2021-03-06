package com.dstakhanov.findealscommithistory.data.info.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InstrumentInfoJsonContainerDto(
    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)