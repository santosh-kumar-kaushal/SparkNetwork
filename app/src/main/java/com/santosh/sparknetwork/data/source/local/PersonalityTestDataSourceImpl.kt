package com.santosh.sparknetwork.data.source.local

import com.santosh.sparknetwork.domain.datasource.PersonalityTestDataSource
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import io.reactivex.Completable
import io.reactivex.Single

class PersonalityTestDataSourceImpl(
    private val dao: PersonalityTestDao
) : PersonalityTestDataSource {

    override fun insertPersonalityTestAnswer(personalityTestData: PersonalityTestData): Completable =
        Completable.fromSingle<PersonalityTestData> { dao.insert(personalityTestData) }

    override fun getAllPersonalityTestAnswers(): Single<List<PersonalityTestData>> =
        Single.create { it.onSuccess(dao.loadAll()) }

    override fun deleteAllPersonalityTestAnswers(): Completable =
        Completable.fromAction { dao.deleteAll() }


}