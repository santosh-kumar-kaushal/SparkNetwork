package com.santosh.sparknetwork.data.source.remote

import com.santosh.sparknetwork.data.interactor.SparkNetworkInteractor
import com.santosh.sparknetwork.domain.model.SparkNetwork
import com.santosh.sparknetwork.domain.datasource.SparkNetworkDataSource
import io.reactivex.Single

class SparkNetworkDataSourceImpl(private val sparkNetworkInteractor: SparkNetworkInteractor) : SparkNetworkDataSource {

    override fun getPersonalityTestQuestionList(fileName: String): Single<SparkNetwork> = sparkNetworkInteractor.getPersonalityTestQuestionList(fileName)

}