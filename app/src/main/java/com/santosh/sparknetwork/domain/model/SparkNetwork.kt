package com.santosh.sparknetwork.domain.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SparkNetwork(
    @SerializedName("categories")
    @Expose
    var categories: List<String>,
    @SerializedName("questions")
    @Expose
    var questions: List<Question>
): Parcelable