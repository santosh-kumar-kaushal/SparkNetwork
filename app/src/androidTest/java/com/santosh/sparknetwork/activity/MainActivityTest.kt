package com.santosh.sparknetwork.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.santosh.sparknetwork.MainActivity
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.util.DisplayedMatchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @Test
    fun test_content_visibility() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(DisplayedMatchers.displayedWithId((R.id.nav_host_fragment))).check(matches(isDisplayed()))
    }
}