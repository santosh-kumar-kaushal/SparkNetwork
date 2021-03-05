package com.santosh.sparknetwork.domain.datasource

import com.santosh.sparknetwork.domain.model.SparkNetwork
import io.reactivex.Single

interface SparkNetworkDataSource {
    fun getPersonalityTestQuestionList(fileName: String): Single<SparkNetwork>
}