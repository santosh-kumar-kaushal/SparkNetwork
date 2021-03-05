package com.santosh.sparknetwork.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SparkNetwork(
    @SerializedName("categories")
    @Expose
    var categories: List<String>,
    @SerializedName("questions")
    @Expose
    var questions: List<Question>
)