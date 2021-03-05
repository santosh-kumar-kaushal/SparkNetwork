package com.santosh.sparknetwork.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IfPositive(
    @SerializedName("question")
    @Expose
    var question: String,
    @SerializedName("category")
    @Expose
    var category: String,
    @SerializedName("question_type")
    @Expose
    var questionType: QuestionTypeForImportant)