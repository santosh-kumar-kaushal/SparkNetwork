package com.santosh.sparknetwork.domain.usecase

import com.santosh.sparknetwork.domain.datasource.PersonalityTestDataSource
import com.santosh.sparknetwork.domain.model.SparkNetwork
import com.santosh.sparknetwork.domain.datasource.SparkNetworkDataSource
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import io.reactivex.Completable
import io.reactivex.Single

class GetPersonalityQuestionUseCase(private val sparkNetworkDataSource: SparkNetworkDataSource,private val personalityTestDataSource: PersonalityTestDataSource) {

    fun getPersonalityTestList(fileName: String): Single<SparkNetwork> = sparkNetworkDataSource.getPersonalityTestList(fileName)

    fun storeData(personalityTestData: PersonalityTestData):Completable = personalityTestDataSource.insertPersonalityTestAnswer(personalityTestData)
}
