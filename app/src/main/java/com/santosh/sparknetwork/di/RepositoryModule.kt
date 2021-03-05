package com.santosh.sparknetwork.di

import com.santosh.sparknetwork.data.interactor.SparkNetworkInteractor
import com.santosh.sparknetwork.data.source.local.PersonalityTestDataSourceImpl
import com.santosh.sparknetwork.data.source.remote.SparkNetworkDataSourceImpl
import com.santosh.sparknetwork.data.source.local.PersonalityTestDao
import org.koin.dsl.module.module

val repositoryModule = module {

    fun providePersonalityTestRepository(dao: PersonalityTestDao): PersonalityTestDataSourceImpl {
        return PersonalityTestDataSourceImpl(
            dao
        )
    }

    fun provideSparkNetworkRepository(interactor: SparkNetworkInteractor): SparkNetworkDataSourceImpl {
        return SparkNetworkDataSourceImpl(
            interactor
        )
    }
    single { providePersonalityTestRepository(get()) }
    single { provideSparkNetworkRepository(get()) }

}