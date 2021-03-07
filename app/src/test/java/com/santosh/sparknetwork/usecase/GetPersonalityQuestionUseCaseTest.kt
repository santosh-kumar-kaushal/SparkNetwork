package com.santosh.sparknetwork.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santosh.sparknetwork.domain.datasource.PersonalityTestDataSource
import com.santosh.sparknetwork.domain.datasource.SparkNetworkDataSource
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import com.santosh.sparknetwork.domain.model.SparkNetwork
import com.santosh.sparknetwork.domain.usecase.GetPersonalityQuestionUseCase
import com.santosh.sparknetwork.util.RxImmediateSchedulerRule
import com.santosh.sparknetwork.util.mock
import com.santosh.sparknetwork.util.whenever
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetPersonalityQuestionUseCaseTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var sparkNetworkDataSource: SparkNetworkDataSource

    @Mock
    lateinit var personalityTestDataSource: PersonalityTestDataSource

    private lateinit var getPersonalityQuestionUseCase: GetPersonalityQuestionUseCase

    private val fileName = "personality_test.json"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        getPersonalityQuestionUseCase = GetPersonalityQuestionUseCase(sparkNetworkDataSource, personalityTestDataSource)
    }

    @Test
    fun testGetPersonalityTestList() {
        val sparkNetwork = mock<SparkNetwork>()
        whenever(sparkNetworkDataSource.getPersonalityTestList(fileName)).thenReturn(Single.just(sparkNetwork))
        val testObserver = TestObserver<SparkNetwork>()

        getPersonalityQuestionUseCase.getPersonalityTestList(fileName).subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertValue(sparkNetwork)

    }

    @Test
    fun testStoreData() {
        val personalityTestData= mock<PersonalityTestData>()
        whenever(personalityTestDataSource.insertPersonalityTestAnswer(personalityTestData)).thenReturn(Completable.complete())

        val testObserver = TestObserver<SparkNetwork>()

        getPersonalityQuestionUseCase.storePersonalityTestData(personalityTestData).subscribe(testObserver)
        testObserver.assertComplete()
    }

}