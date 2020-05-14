package com.juliuscanute.multiconfig.config

import com.juliuscanute.multiconfig.model.ConfigurationManager

interface Starter {
    fun getConfigurationManager(): ConfigurationManager
}