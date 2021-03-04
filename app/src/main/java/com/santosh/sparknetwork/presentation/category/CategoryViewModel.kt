package com.santosh.sparknetwork.presentation.category

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santosh.sparknetwork.data.source.remote.ApiService
import com.santosh.sparknetwork.data.source.remote.RxSingleSchedulers
import com.santosh.sparknetwork.domain.model.SparkNetwork
import io.reactivex.schedulers.Schedulers

class CategoryViewModel(
    private val apiService: ApiService,
    private val schedulers: RxSingleSchedulers
) : ViewModel() {

    private val userList = MutableLiveData<List<SparkNetwork>>()

    @SuppressLint("CheckResult")
    fun fetchPersonalityTestQuestionList() {
        apiService.getPersonalityTestQuestionList().subscribeOn(Schedulers.io())
            .compose(schedulers.applySchedulers())
            .subscribe({ result -> userList.postValue(result) },
                { throwable ->

                })

    }

}