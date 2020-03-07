package com.juliuscanute.multiconfig.di

import com.juliuscanute.multiconfig.setup
import com.juliuscanute.multiconfig.ui.configdetail.ConfigurationDetailViewModel
import com.juliuscanute.multiconfig.ui.config.ConfigurationViewModel
import com.juliuscanute.multiconfig.ui.host.MainActivityViewModel
import model.ConfigurationManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import settings.UserSettings

val dependencyModule = module {
    single { setup() }
    single { UserSettings().userSettings(androidContext()) }
    single { ConfigurationManager(get(), get()) }
    viewModel { ConfigurationDetailViewModel(get()) }
    viewModel { ConfigurationViewModel(get()) }
    viewModel { MainActivityViewModel() }
}