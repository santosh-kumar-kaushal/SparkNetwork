package com.santosh.sparknetwork.di

import com.santosh.sparknetwork.data.source.local.AppDatabase
import com.santosh.sparknetwork.data.source.local.PersonalityTestDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val databaseModule = module {

    fun providePersonalityTestRepositoryDao(database: AppDatabase): PersonalityTestDao {
        return  database.personalityTestDao
    }
    single { AppDatabase.getInstance(androidContext()) }
    single { providePersonalityTestRepositoryDao(get()) }


}