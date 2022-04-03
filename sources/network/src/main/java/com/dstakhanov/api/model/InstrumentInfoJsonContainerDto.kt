package com.dstakhanov.api.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InstrumentInfoJsonContainerDto(
    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)