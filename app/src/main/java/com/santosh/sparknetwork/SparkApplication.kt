package com.santosh.sparknetwork

import android.app.Application
import com.santosh.sparknetwork.di.networkModule
import com.santosh.sparknetwork.di.viewModelModule
import org.koin.android.ext.android.startKoin

class SparkApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                networkModule,
                viewModelModule
            )
        )
    }
}
