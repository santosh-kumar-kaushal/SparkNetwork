package com.santosh.sparknetwork.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Range(
    @SerializedName("from") @Expose
    var from: Int,
    @SerializedName("to")
    @Expose
    var to: Int
)