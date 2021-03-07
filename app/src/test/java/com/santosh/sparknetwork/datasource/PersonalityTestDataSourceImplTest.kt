package com.santosh.sparknetwork.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santosh.sparknetwork.data.source.local.PersonalityTestDao
import com.santosh.sparknetwork.data.source.local.PersonalityTestDataSourceImpl
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import com.santosh.sparknetwork.util.RxImmediateSchedulerRule
import com.santosh.sparknetwork.util.mock
import com.santosh.sparknetwork.util.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class PersonalityTestDataSourceImplTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var personalityTestDao: PersonalityTestDao

    private lateinit var personalityTestDataSourceImpl: PersonalityTestDataSourceImpl

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        personalityTestDataSourceImpl = PersonalityTestDataSourceImpl(personalityTestDao)
    }

    @Test
    fun testGetAllPersonalityTestAnswers() {
        val listofPersonalityTestData = listOf<PersonalityTestData>()
        whenever(personalityTestDao.loadAll()).thenReturn(listofPersonalityTestData)

        personalityTestDataSourceImpl.getAllPersonalityTestAnswers().test().run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            assertValue(listofPersonalityTestData)
            verify(personalityTestDao).loadAll()
        }
    }


    @Test
    fun testInsertSparkData() {
        val personalityTestData= mock<PersonalityTestData>()
        personalityTestDataSourceImpl.insertPersonalityTestAnswer(personalityTestData).test().run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            verify(personalityTestDao).insert(personalityTestData)
        }
    }

    @Test
    fun testDeleteSparkData() {
        personalityTestDataSourceImpl.deleteAllPersonalityTestAnswers().test().run {
            awaitTerminalEvent()
            assertComplete()
            assertNoErrors()
            verify(personalityTestDao).deleteAll()
        }
    }



}