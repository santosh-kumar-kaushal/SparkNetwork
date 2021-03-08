package com.santosh.sparknetwork.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.santosh.sparknetwork.data.source.remote.RxSingleSchedulers
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import com.santosh.sparknetwork.domain.model.Question
import com.santosh.sparknetwork.domain.model.SparkNetwork
import com.santosh.sparknetwork.domain.usecase.GetPersonalityQuestionUseCase
import com.santosh.sparknetwork.presentation.viewmodel.SharedViewModel
import com.santosh.sparknetwork.util.*
import io.reactivex.Completable
import io.reactivex.Single
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class SharedViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule =
        RxImmediateSchedulerRule()

    @Mock
    lateinit var useCaseMocked:GetPersonalityQuestionUseCase

    @Mock
    lateinit var observer: Observer<UIState>

    private lateinit var viewModel: SharedViewModel

    private val fileName="personality_test.json"

    private val category="hard_fact"


    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = SharedViewModel(useCaseMocked,RxSingleSchedulers.DEFAULT)
        viewModel.uiState.observeForever(observer)
    }

    @Test
    fun testNull() {
        whenever(
            useCaseMocked.getPersonalityTestList(
                fileName
            )
        ).thenReturn(null)
        assertNotNull(viewModel.uiState)
        assertTrue(viewModel.uiState.hasObservers())
    }

    @Test
    fun testApiFetchDataSuccess() {
         val sparkNetwork= mock<SparkNetwork>()
        whenever(
            useCaseMocked.getPersonalityTestList(
                fileName
            )
        ).thenReturn(Single.just(sparkNetwork))
        viewModel.fetchSparkNetworkPersonalityTestList(fileName)
        verify(observer).onChanged(LoadingState)
        verify(observer).onChanged(RetrievedPostState(sparkNetwork))
    }

    @Test
    fun testApiFetchDataError() {
        whenever(
            useCaseMocked.getPersonalityTestList(
                fileName
            )
        ).thenReturn(Single.error(Throwable("Api error")))
        viewModel.fetchSparkNetworkPersonalityTestList(fileName)
        verify(observer).onChanged(LoadingState)
        verify(observer).onChanged(ErrorState("Api error"))
    }

    @Test
    fun testFilterQuestionsBasedOnCategory() {
        val list= listOf<Question>()
        viewModel.filteredQuestion(list,category)
        assertNotNull(list)
    }

    @Test
    fun testPersonalityDataStore() {
        val personalityTestData= mock<PersonalityTestData>()
        whenever(
            useCaseMocked.storePersonalityTestData(personalityTestData)
        ).thenReturn(Completable.complete())
        viewModel.storeData(personalityTestData)
        verify(useCaseMocked).storePersonalityTestData(personalityTestData)
    }

    @Test
    fun testApiPostPersonalityTestAnswerData() {
        whenever(useCaseMocked.postPersonalityTestData()).thenReturn(Completable.complete())
        viewModel.postPersonalityTestData()
        verify(useCaseMocked).postPersonalityTestData()
    }
}