package com.dstakhanov.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InstrumentNameDto(
    @SerializedName("Name")
    @Expose
    val name: String? = null
)