package com.santosh.sparknetwork.domain.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Range(
    @SerializedName("from") @Expose
    var from: Int,
    @SerializedName("to")
    @Expose
    var to: Int
):Parcelable