package com.santosh.sparknetwork.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santosh.sparknetwork.data.source.remote.RxSingleSchedulers
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import com.santosh.sparknetwork.domain.model.Question
import com.santosh.sparknetwork.domain.model.SparkNetwork
import com.santosh.sparknetwork.domain.usecase.GetPersonalityQuestionUseCase
import com.santosh.sparknetwork.util.*
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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
        _uiState.value = RetrievedPostState(sparkNetwork)
    }

    private fun handleFailure(t: Throwable) {
        _uiState.value =
            ErrorState(
                t.message.toString()
            )
    }

    @SuppressLint("CheckResult")
    fun fetchSparkNetworkPersonalityTestList(fileName: String) {
        getPersonalityQuestionUseCase.getPersonalityTestList(fileName)
            .compose(rxSingleSchedulers.applySchedulers())
            .doOnSubscribe { handleLoadingState() }
            .map { sparkNetwork -> sparkNetwork }
            .subscribe(this::handlePostCategoryList, this::handleFailure)
    }

    fun storeData(personalityTestData: PersonalityTestData) {
        getPersonalityQuestionUseCase.storePersonalityTestData(personalityTestData)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }

    fun filteredQuestion(questionList: List<Question>, category: String): List<Question> {
        val filteredQuestList: MutableList<Question> = mutableListOf()
        for (question in questionList) {
            if (category == question.category) {
                filteredQuestList.add(question)
            }
        }
        return filteredQuestList
    }

    fun postPersonalityTestData(): Completable = Completable.fromAction {
        getPersonalityQuestionUseCase.postPersonalityTestData().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }
}