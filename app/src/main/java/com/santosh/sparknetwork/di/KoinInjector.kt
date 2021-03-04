package com.santosh.sparknetwork.di

import com.santosh.sparknetwork.presentation.category.CategoryViewModel
import com.santosh.sparknetwork.data.source.remote.RxSingleSchedulers
import com.santosh.sparknetwork.data.source.remote.buildRetrofitInstance
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val networkModule = module {
    single { buildRetrofitInstance() }
    single { RxSingleSchedulers.DEFAULT }
}

val viewModelModule = module {

    viewModel {
        CategoryViewModel(
            get(),
            get()
        )
    }
}