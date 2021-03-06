package com.santosh.sparknetwork.domain.datasource

import com.santosh.sparknetwork.domain.model.PersonalityTestData
import io.reactivex.Completable
import io.reactivex.Single

interface PersonalityTestDataSource {

    fun insertPersonalityTestAnswer(personalityTestData: PersonalityTestData):Completable

    fun getAllPersonalityTestAnswers(): Single<List<PersonalityTestData>>

    fun deleteAllPersonalityTestAnswers(): Completable
}