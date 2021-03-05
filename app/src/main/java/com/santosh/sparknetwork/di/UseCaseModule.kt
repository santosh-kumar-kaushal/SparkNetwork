package com.santosh.sparknetwork.di

import com.santosh.sparknetwork.data.source.remote.SparkNetworkDataSourceImpl
import com.santosh.sparknetwork.domain.usecase.GetPersonalityQuestionUseCase
import org.koin.dsl.module.module

val useCaseModule = module {

    fun providePersonalityQuestionUseCase(sparkNetworkRepositoryImpl: SparkNetworkDataSourceImpl): GetPersonalityQuestionUseCase {
        return GetPersonalityQuestionUseCase(sparkNetworkRepositoryImpl)
    }

    single { providePersonalityQuestionUseCase(get()) }
}