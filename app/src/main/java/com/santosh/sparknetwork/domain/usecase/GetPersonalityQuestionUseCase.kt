package com.santosh.sparknetwork.domain.usecase

import android.annotation.SuppressLint
import com.santosh.sparknetwork.domain.datasource.PersonalityTestDataSource
import com.santosh.sparknetwork.domain.model.SparkNetwork
import com.santosh.sparknetwork.domain.datasource.SparkNetworkDataSource
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import com.santosh.sparknetwork.domain.model.PersonalityTestDataBody
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private const val USER_ID = "6789"

class GetPersonalityQuestionUseCase(
    private val sparkNetworkDataSource: SparkNetworkDataSource,
    private val personalityTestDataSource: PersonalityTestDataSource
) {

    fun getPersonalityTestList(fileName: String): Single<SparkNetwork> =
        sparkNetworkDataSource.getPersonalityTestList(fileName)

    fun storePersonalityTestData(personalityTestData: PersonalityTestData): Completable =
        personalityTestDataSource.insertPersonalityTestAnswer(personalityTestData)

    @SuppressLint("CheckResult")
    fun postPersonalityTestData() : Completable {
        return Completable.fromAction {
            personalityTestDataSource.getAllPersonalityTestAnswers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { personalityTestDataList -> personalityTestDataList }
                .subscribe(this::handlePostCategoryList)
        }
    }

    private fun handlePostCategoryList(personalityTestDataList: List<PersonalityTestData>) {
        sparkNetworkDataSource.postPersonalityTestAnswerList(
            USER_ID,
            PersonalityTestDataBody(personalityTestDataList)
        )
    }
}
