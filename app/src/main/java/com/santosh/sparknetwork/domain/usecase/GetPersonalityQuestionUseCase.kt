package com.santosh.sparknetwork.domain.usecase

import com.santosh.sparknetwork.domain.model.SparkNetwork
import com.santosh.sparknetwork.domain.datasource.SparkNetworkDataSource
import io.reactivex.Single

class GetPersonalityQuestionUseCase(private val sparkNetworkDataSource: SparkNetworkDataSource) {

    fun getPersonalityTestQuestionList(fileName: String): Single<SparkNetwork> = sparkNetworkDataSource.getPersonalityTestQuestionList(fileName)
}
