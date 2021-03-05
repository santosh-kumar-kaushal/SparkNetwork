package com.santosh.sparknetwork.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santosh.sparknetwork.data.source.remote.RxSingleSchedulers
import com.santosh.sparknetwork.domain.model.SparkNetwork
import com.santosh.sparknetwork.domain.usecase.GetPersonalityQuestionUseCase
import com.santosh.sparknetwork.util.*

class SharedViewModel(
    private var getPersonalityQuestionUseCase: GetPersonalityQuestionUseCase,
    private val rxSingleSchedulers: RxSingleSchedulers
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>().apply {
        value = DefaultState
    }

    val uiState: LiveData<UIState>
        get() = _uiState

    private fun handleLoadingState() {
        _uiState.value = LoadingState
    }

    private fun handlePostCategoryList(sparkNetwork: SparkNetwork) {
        _uiState.value = RetrievedPostState(sparkNetwork.categories)
    }

    private fun handleFailure(t: Throwable) {
        _uiState.value =
            ErrorState(
                t.message.toString()
            )
    }

    @SuppressLint("CheckResult")
    fun fetchCategoryList(fileName: String) {
        getPersonalityQuestionUseCase.getPersonalityTestQuestionList(fileName)
            .compose(rxSingleSchedulers.applySchedulers())
            .doOnSubscribe { handleLoadingState() }
            .map { sparkNetwork -> sparkNetwork }
            .subscribe(this::handlePostCategoryList, this::handleFailure)
    }
}