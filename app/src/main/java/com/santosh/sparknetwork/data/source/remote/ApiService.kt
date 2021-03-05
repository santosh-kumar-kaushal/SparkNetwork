package com.santosh.sparknetwork.data.source.remote

import com.santosh.sparknetwork.domain.model.SparkNetwork
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("sparknetworks/coding_exercises_options/master/personality_test/database/{fileName}")
    fun getPersonalityTestQuestionList(@Path("fileName") fileName:String) : Single<SparkNetwork>
}