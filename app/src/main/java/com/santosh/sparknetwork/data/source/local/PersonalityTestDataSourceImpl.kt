package com.santosh.sparknetwork.data.source.local

import com.santosh.sparknetwork.domain.datasource.PersonalityTestDataSource
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import io.reactivex.Completable

class PersonalityTestDataSourceImpl(
    private val dao: PersonalityTestDao
) : PersonalityTestDataSource {

    override fun insertPersonalityTestAnswer(personalityTestData: PersonalityTestData):Completable {
        return  Completable.fromSingle<PersonalityTestData> {  dao.insert(personalityTestData) }
    }

    override fun getAllPersonalityTestAnswers(): MutableList<PersonalityTestData> {
        return dao.loadAll()
    }

    override fun deleteAllPersonalityTestAnswers() {
        dao.deleteAll()
    }


}