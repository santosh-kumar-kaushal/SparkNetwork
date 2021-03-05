package com.santosh.sparknetwork.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuestionTypeForImportant(
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("range")
    @Expose
    var range: Range
)