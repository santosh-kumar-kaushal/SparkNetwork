package com.santosh.sparknetwork.data.source.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL="https://raw.githubusercontent.com/"

private fun httpInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun buildOkHttpClient() = OkHttpClient.Builder().addInterceptor(httpInterceptor()).build()

fun buildRetrofitInstance(): ApiService {
    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).client(buildOkHttpClient())
        .baseUrl(BASE_URL)
        .build()
    return retrofit.create(ApiService::class.java);
}
