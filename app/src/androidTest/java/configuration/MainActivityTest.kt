package configuration

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import builder.appConfig
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    @After
    fun afterTestsRun() {
        stopKoin()
    }

    @Test
    fun showConfigurationList() {
        loadKoinModules(module(override = true) {
            single {
                appConfig {
                    config("DEV") {

                    }
                    config("SIT") {

                    }
                    config("UAT") {

                    }
                    config("PROD") {

                    }
                }
            }
            viewModel { MainActivityViewModel(get()) }
        })
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