package com.santosh.sparknetwork.di

import com.google.gson.GsonBuilder
import com.santosh.sparknetwork.data.source.remote.ApiService
import com.santosh.sparknetwork.data.source.remote.RxSingleSchedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 10L

private const val BASE_URL = "https://raw.githubusercontent.com/"

val networkModule = module {

    fun httpInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun buildApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiService::class.java);
    }

    single { GsonBuilder().create() }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(httpInterceptor())
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }.build()
    }

    single { buildApiService() }
    single { RxSingleSchedulers.DEFAULT }


}