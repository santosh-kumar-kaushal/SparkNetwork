package com.santosh.sparknetwork.data.interactor

import com.santosh.sparknetwork.data.source.remote.ApiService
import com.santosh.sparknetwork.domain.model.SparkNetwork
import io.reactivex.Single

class SparkNetworkInteractor(private val apiService: ApiService) {

    fun getPersonalityTestQuestionList(fileName: String): Single<SparkNetwork> = apiService.getPersonalityTestQuestionList(fileName)
}