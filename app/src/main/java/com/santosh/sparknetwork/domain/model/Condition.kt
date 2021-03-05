package com.santosh.sparknetwork.domain.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Condition(@SerializedName("predicate")
                     @Expose var predicate: Predicate?,
                     @SerializedName("if_positive")
                     @Expose var ifPositive: IfPositive): Parcelable
