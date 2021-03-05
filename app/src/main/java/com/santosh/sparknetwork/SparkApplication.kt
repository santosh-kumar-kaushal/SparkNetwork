package com.santosh.sparknetwork

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.multidex.MultiDex
import com.santosh.sparknetwork.di.*
import org.koin.android.ext.android.startKoin

class SparkApplication : Application() {

    companion object {
        lateinit var instance: SparkApplication

        fun isNetworkAvailable(): Boolean {
            val cm = instance.applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo?.isConnected ?: false
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        MultiDex.install(this)
        startKoin(
            this, listOf(
                networkModule,
                databaseModule,
                useCaseModule,
                repositoryModule,
                interactorModule,
                viewModelModule
            )
        )
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
