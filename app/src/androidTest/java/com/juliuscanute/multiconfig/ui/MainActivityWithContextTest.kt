package com.juliuscanute.multiconfig.ui

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.builder.appConfig
import com.juliuscanute.multiconfig.config.startMultiConfig
import com.juliuscanute.multiconfig.ui.host.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityWithContextTest {

    @Before
    fun setUp() {
        startMultiConfig(context = InstrumentationRegistry.getInstrumentation().context, intent = Intent()) {
            multiConfig(configuration = appConfig {
                config("DEV") {

                    switch {
                        key = "A"
                        description = "A-D"
                        switchValue = false
                    }
                }

                config("SIT") {

                    switch {
                        key = "A"
                        description = "A-D"
                        switchValue = true
                    }
                }

                config("UAT") {

                    switch {
                        key = "A"
                        description = "A-D"
                        switchValue = false
                    }
                }
            })
        }
    }

    @Test
    fun showConfigurationList() {
        ActivityScenario.launch(MainActivity::class.java)
        isItemDisplayed(0, "DEV", R.id.environment)
        isItemDisplayed(1, "SIT", R.id.environment)
        isItemDisplayed(2, "UAT", R.id.environment)
        onView(withText("SIT")).perform(click())
        onView(withId(R.id.launchButton)).perform(click())
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