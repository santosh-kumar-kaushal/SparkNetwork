package com.santosh.sparknetwork.data.interactor

import com.santosh.sparknetwork.data.source.remote.ApiService
import com.santosh.sparknetwork.domain.model.PersonalityTestDataBody
import com.santosh.sparknetwork.domain.model.SparkNetwork
import io.reactivex.Completable
import io.reactivex.Single

class SparkNetworkInteractor(private val apiService: ApiService) {

    fun getPersonalityTestList(fileName: String): Single<SparkNetwork> = apiService.getPersonalityTestQuestionList(fileName)

    fun postPersonalityTestAnsweredList(userID: String,personalityTestDataBody: PersonalityTestDataBody): Completable = apiService.postAnsweredQuestion(userID,personalityTestDataBody)
}