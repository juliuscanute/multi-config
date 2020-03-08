package com.juliuscanute.multiconfig.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.ui.host.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    @Test
    fun showConfigurationList() {
        ActivityScenario.launch(MainActivity::class.java)
        isItemDisplayed(0, "DEV", R.id.environment)
        isItemDisplayed(1, "SIT", R.id.environment)
        isItemDisplayed(2, "UAT", R.id.environment)
        isItemDisplayed(3, "PROD", R.id.environment)
    }

    private fun isItemDisplayed(position: Int, text: String, viewId: Int) {
        onView(withId(R.id.configuration_list)).check(
            matches(
                recyclerViewAtPositionOnView(
                    position,
                    withText(text),
                    viewId
                )
            )
        )
    }
}