package com.santosh.sparknetwork.data.source.remote

import com.santosh.sparknetwork.domain.model.SparkNetwork
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("sparknetworks/coding_exercises_options/master/personality_test/database/personality_test.json")
    fun getPersonalityTestQuestionList() : Single<List<SparkNetwork>>
}