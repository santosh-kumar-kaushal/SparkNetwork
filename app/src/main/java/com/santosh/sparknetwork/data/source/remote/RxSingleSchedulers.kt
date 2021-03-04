package com.santosh.sparknetwork.data.source.remote

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface RxSingleSchedulers {

    fun <T> applySchedulers(): SingleTransformer<T, T>

    companion object {
        val DEFAULT: RxSingleSchedulers = object :
            RxSingleSchedulers {
            override fun <T> applySchedulers(): SingleTransformer<T, T> {
                return SingleTransformer { single ->
                    single
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                }
            }
        }
    }
}