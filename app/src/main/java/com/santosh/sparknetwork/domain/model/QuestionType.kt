package com.santosh.sparknetwork.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuestionType(
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("options")
    @Expose
    var options: List<String>,
    @SerializedName("condition")
    @Expose
    var condition: Condition
)