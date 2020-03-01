package configuration

import model.ConfigurationManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import settings.UserSettings

val dependencyModule = module {
    single { setup() }
    single { UserSettings().userSettings(androidContext()) }
    single { ConfigurationManager(get(), get()) }
    viewModel { MainActivityViewModel(get()) }
}