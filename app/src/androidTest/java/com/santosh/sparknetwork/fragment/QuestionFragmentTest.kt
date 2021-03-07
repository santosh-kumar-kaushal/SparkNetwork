package com.santosh.sparknetwork.fragment

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.santosh.sparknetwork.util.DisplayedMatchers
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.domain.model.Question
import com.santosh.sparknetwork.presentation.category.CURRENT_POSITION
import com.santosh.sparknetwork.presentation.category.QUESTION_LIST
import com.santosh.sparknetwork.presentation.questions.QuestionFragment
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class QuestionFragmentTest{

    @Test
    fun testQuestionItemVisibility() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()).apply {
            UiThreadStatement.runOnUiThread {
                setGraph(R.navigation.nav_graph)
                setCurrentDestination(R.id.questionFragment)
            }
        }
        val questionList= listOf<Question>()
        val bundle = bundleOf(QUESTION_LIST to questionList, CURRENT_POSITION to 0)
        val questionFragment = launchFragmentInContainer<QuestionFragment>(bundle,factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return QuestionFragment()
            }
        }, themeResId = R.style.Theme_SparkNetwork)

        questionFragment.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onData(Matchers.anything())
            .inAdapterView(allOf(
                DisplayedMatchers.displayedWithId(
                    R.id.nextButton
                ), ViewMatchers.isCompletelyDisplayed()))
            .atPosition(0)
            .check(matches(
                DisplayedMatchers.displayedWithText(
                    "NEXT"
                )
            ))
            .perform(click())
    }
}