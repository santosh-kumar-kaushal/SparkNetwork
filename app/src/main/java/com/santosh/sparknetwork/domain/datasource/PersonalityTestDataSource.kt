package com.santosh.sparknetwork.domain.datasource

import com.santosh.sparknetwork.domain.model.PersonalityTestData
import io.reactivex.Completable

interface PersonalityTestDataSource {

    fun insertPersonalityTestAnswer(personalityTestData: PersonalityTestData):Completable

    fun getAllPersonalityTestAnswers(): MutableList<PersonalityTestData>

    fun deleteAllPersonalityTestAnswers()
}