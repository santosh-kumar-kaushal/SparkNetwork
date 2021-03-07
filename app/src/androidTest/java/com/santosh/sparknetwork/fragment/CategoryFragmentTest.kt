package com.santosh.sparknetwork.fragment

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
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.google.common.truth.Truth.assertThat
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.presentation.category.CURRENT_POSITION
import com.santosh.sparknetwork.presentation.category.CategoryFragment
import com.santosh.sparknetwork.util.DisplayedMatchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class CategoryFragmentTest {

    @Test
    fun testNavigationToQuestionFragment() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()).apply {
            runOnUiThread {
                setGraph(R.navigation.nav_graph)
                setCurrentDestination(R.id.categoryFragment)
            }
        }
        val bundle = bundleOf( CURRENT_POSITION to 0)
        val categoryFragment = launchFragmentInContainer<CategoryFragment>(bundle,factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return CategoryFragment()
            }
        }, themeResId = R.style.Theme_SparkNetwork)

        categoryFragment.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        Thread.sleep(5000)
        onData(anything())
            .inAdapterView(allOf(
                DisplayedMatchers.displayedWithId(
                    R.id.nextButton
                ), isCompletelyDisplayed()))
            .atPosition(0)
            .check(matches(withText("ANSWER")))
           .perform(click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.questionFragment)
    }
}