package com.santosh.sparknetwork.domain.datasource

import com.santosh.sparknetwork.domain.model.PersonalityTestDataBody
import com.santosh.sparknetwork.domain.model.SparkNetwork
import io.reactivex.Completable
import io.reactivex.Single

interface SparkNetworkDataSource {
    fun getPersonalityTestList(fileName: String): Single<SparkNetwork>

    fun postPersonalityTestAnswerList(userID: String,personalityTestDataBody: PersonalityTestDataBody): Completable
}