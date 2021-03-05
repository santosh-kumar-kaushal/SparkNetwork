package com.santosh.sparknetwork.data.source.local

import com.santosh.sparknetwork.data.source.local.PersonalityTestDao
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import com.santosh.sparknetwork.domain.datasource.PersonalityTestDataSource

class PersonalityTestDataSourceImpl(
    private val dao: PersonalityTestDao
) : PersonalityTestDataSource {

    override fun insertPersonalityTestAnswer(personalityTestData: PersonalityTestData) {
        dao.insert(personalityTestData)
    }

    override fun getAllPersonalityTestAnswers(): MutableList<PersonalityTestData> {
        return dao.loadAll()
    }

    override fun deleteAllPersonalityTestAnswers() {
        dao.deleteAll()
    }


}