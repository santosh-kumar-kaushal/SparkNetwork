package com.santosh.sparknetwork.domain.datasource

import com.santosh.sparknetwork.domain.model.PersonalityTestData

interface PersonalityTestDataSource {

    fun insertPersonalityTestAnswer(personalityTestData: PersonalityTestData)

    fun getAllPersonalityTestAnswers(): MutableList<PersonalityTestData>

    fun deleteAllPersonalityTestAnswers()
}