package com.santosh.sparknetwork.domain.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Predicate(
    @SerializedName("exactEquals")
    @Expose var exactEquals: List<String>): Parcelable