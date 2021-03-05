package com.santosh.sparknetwork.di

import com.santosh.sparknetwork.presentation.viewmodel.SharedViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel {
        SharedViewModel(
            get(),
            get()
        )
    }
}