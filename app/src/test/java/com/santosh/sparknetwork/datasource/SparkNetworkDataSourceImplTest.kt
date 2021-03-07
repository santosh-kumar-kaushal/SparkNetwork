package com.santosh.sparknetwork.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santosh.sparknetwork.data.interactor.SparkNetworkInteractor
import com.santosh.sparknetwork.data.source.remote.SparkNetworkDataSourceImpl
import com.santosh.sparknetwork.domain.model.PersonalityTestDataBody
import com.santosh.sparknetwork.domain.model.SparkNetwork
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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class SparkNetworkDataSourceImplTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var sparkNetworkInteractor: SparkNetworkInteractor

    private lateinit var sparkNetworkDataSourceImpl: SparkNetworkDataSourceImpl

    private val fileName = "personality_test.json"
    private val userID="1234"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        sparkNetworkDataSourceImpl = SparkNetworkDataSourceImpl(sparkNetworkInteractor)
    }


    @Test
    fun testFetchPersonalityTestListSuccess() {
        val sparkNetwork = mock<SparkNetwork>()
        whenever(sparkNetworkInteractor.getPersonalityTestList(fileName)).thenReturn(Single.just(sparkNetwork))
        val testObserver = TestObserver<SparkNetwork>()

        sparkNetworkDataSourceImpl.getPersonalityTestList(fileName).subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertValue(sparkNetwork)
    }

    @Test
    fun testFetchPersonalityTestListFailure() {
        whenever(sparkNetworkInteractor.getPersonalityTestList(fileName)).thenReturn(Single.error(Exception()))
        val testObserver = TestObserver<SparkNetwork>()

        sparkNetworkDataSourceImpl.getPersonalityTestList(fileName).subscribe(testObserver)

        testObserver.assertNotComplete()
        testObserver.assertError(Exception::class.java)
    }

    @Test
    fun testPostPersonalityTestListSuccess() {
        val personalityTestDataBody = mock<PersonalityTestDataBody>()
        whenever(sparkNetworkInteractor.postPersonalityTestAnsweredList(userID,personalityTestDataBody)).thenReturn(
            Completable.complete())
        val testObserver = TestObserver<PersonalityTestDataBody>()

        sparkNetworkDataSourceImpl.postPersonalityTestAnswerList(userID,personalityTestDataBody).subscribe(testObserver)

        testObserver.assertComplete()
    }

    @Test
    fun testPostPersonalityTestListFailure() {
        val personalityTestDataBody = mock<PersonalityTestDataBody>()
        whenever(sparkNetworkInteractor.postPersonalityTestAnsweredList(userID,personalityTestDataBody)).thenReturn(
            Completable.error(Exception()))
        val testObserver = TestObserver<PersonalityTestDataBody>()

        sparkNetworkDataSourceImpl.postPersonalityTestAnswerList(userID,personalityTestDataBody).subscribe(testObserver)

        testObserver.assertNotComplete()
        testObserver.assertError(Exception::class.java)
    }
}