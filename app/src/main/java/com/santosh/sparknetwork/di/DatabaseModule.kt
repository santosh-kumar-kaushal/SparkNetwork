package com.santosh.sparknetwork.di

import android.app.Application
import androidx.room.Room
import com.santosh.sparknetwork.data.source.local.AppDatabase
import com.santosh.sparknetwork.data.source.local.PersonalityTestDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "sparkAppDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun providePersonalityTestRepositoryDao(database: AppDatabase): PersonalityTestDao {
        return  database.personalityTestDao
    }

    single { provideDatabase(androidApplication()) }
    single { providePersonalityTestRepositoryDao(get()) }


}