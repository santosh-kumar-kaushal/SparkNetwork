package com.santosh.sparknetwork.di

import com.santosh.sparknetwork.data.interactor.SparkNetworkInteractor
import com.santosh.sparknetwork.data.source.remote.ApiService
import org.koin.dsl.module.module

val interactorModule = module {

    fun provideSparkNetworkInteractor(apiService: ApiService): SparkNetworkInteractor {
        return SparkNetworkInteractor(
            apiService
        )
    }
    single { provideSparkNetworkInteractor(get()) }
}