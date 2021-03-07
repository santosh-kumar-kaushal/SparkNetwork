package com.santosh.sparknetwork.util

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class DisplayedMatchers {

    companion object {
        @NonNull
        fun displayedWithId(@IdRes id: Int): Matcher<View> {
            return allOf(ViewMatchers.isDisplayed(), withId(id))
        }

        @NonNull
        fun displayedWithText(text: String?): Matcher<View> {
            return allOf(ViewMatchers.isDisplayed(), withText(text))
        }
    }
}
