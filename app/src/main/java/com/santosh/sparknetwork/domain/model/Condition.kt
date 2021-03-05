package com.santosh.sparknetwork.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Condition(@SerializedName("predicate")
                     @Expose var predicate: Predicate?,
                     @SerializedName("if_positive")
                     @Expose var ifPositive: IfPositive)
