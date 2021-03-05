package com.santosh.sparknetwork.domain.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionTypeForImportant(
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("range")
    @Expose
    var range: Range
) : Parcelable