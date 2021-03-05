package com.santosh.sparknetwork.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Predicate(
    @SerializedName("exactEquals")
    @Expose var exactEquals: List<String>)