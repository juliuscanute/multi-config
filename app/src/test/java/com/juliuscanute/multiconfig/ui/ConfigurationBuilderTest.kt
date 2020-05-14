package com.juliuscanute.multiconfig.ui

import com.juliuscanute.multiconfig.builder.appConfig
import com.nhaarman.mockitokotlin2.mock
import com.juliuscanute.multiconfig.model.ConfigurationRepository
import com.juliuscanute.multiconfig.model.ImmutableConfigurationRepository
import com.juliuscanute.multiconfig.model.UiControlsModel
import org.junit.Assert
import org.junit.Test
import com.juliuscanute.multiconfig.settings.Settings


class ConfigurationBuilderTest {
    @Test
    fun `verify configuration`() {
        val configs = appConfig {
            config("DEV") {

                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = false
                }

                range {
                    key = "B"
                    description = "B-D"
                    min = 1
                    max = 100
                    currentValue = 50
                }

                editable {
                    key = "C"
                    description = "C-D"
                    currentValue = "C-V"
                }

                choice {
                    key = "D"
                    description = "D-D"
                    currentChoiceIndex = 0
                    item {
                        description = "E"
                    }
                    item {
                        description = "F"
                    }
                    item {
                        description = "G"
                    }
                }

            }
        }[0]

        val config = configs.configs

        config.forEach {
            when (it) {
                is UiControlsModel.Switch -> {
                    Assert.assertEquals("A", it.key)
                    Assert.assertEquals("A-D", it.information)
                    Assert.assertEquals(false, it.switchValue)
                }
                is UiControlsModel.Range -> {
                    Assert.assertEquals("B", it.key)
                    Assert.assertEquals("B-D", it.information)
                    Assert.assertEquals(1, it.min)
                    Assert.assertEquals(100, it.max)
                    Assert.assertEquals(50, it.currentValue)
                }
                is UiControlsModel.Editable -> {
                    Assert.assertEquals("C", it.key)
                    Assert.assertEquals("C-D", it.information)
                    Assert.assertEquals("C-V", it.currentValue)
                }
                is UiControlsModel.Choice -> {
                    Assert.assertEquals("D", it.key)
                    Assert.assertEquals("D-D", it.information)
                    Assert.assertEquals(0, it.currentChoiceIndex)
                    Assert.assertEquals("E", it.items[0].information)
                    Assert.assertEquals("F", it.items[1].information)
                    Assert.assertEquals("G", it.items[2].information)
                }
            }
        }

    }

    @Test
    fun `verify save configuration`() {
        val configs = appConfig {
            config("DEV") {

                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = false
                }

                range {
                    key = "B"
                    description = "B-D"
                    min = 1
                    max = 100
                    currentValue = 50
                }

                editable {
                    key = "C"
                    description = "C-D"
                    currentValue = "C-V"
                }

                choice {
                    key = "D"
                    description = "D-D"
                    currentChoiceIndex = 0
                    item {
                        description = "E"
                    }
                    item {
                        description = "F"
                    }
                    item {
                        description = "G"
                    }
                }

            }
        }[0]

        val config = ConfigurationRepository(configs.configs, mock(), "DEV")

        Assert.assertEquals(50, config.getConfigInt("B"))
        config.saveConfig("B", 75)
        Assert.assertEquals(75, config.getConfigInt("B"))
        Assert.assertEquals("E" to 0, config.getConfigPair("D"))
        config.saveConfig("D", "G" to 2)
        Assert.assertEquals("G" to 2, config.getConfigPair("D"))
    }

    @Test
    fun `verify multi configuration`() {
        val config = appConfig {
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
                    switchValue = false
                }
            }

            config("UAT") {

                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = false
                }
            }
        }
        Assert.assertEquals("DEV", config[0].environment)
        Assert.assertEquals("SIT", config[1].environment)
        Assert.assertEquals("UAT", config[2].environment)
    }

    @Test(expected = IllegalStateException::class)
    fun `verify editable all empty`() {
        appConfig {
            config("DEV") {
                editable {}
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `verify editable only key`() {
        appConfig {
            config("DEV") {
                editable {
                    key = "C"
                }
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `verify editable key & description`() {
        appConfig {
            config("DEV") {
                editable {
                    key = "C"
                    description = "C-D"
                }
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `verify items is not empty`() {
        appConfig {
            config("DEV") {
                choice {
                    key = "D"
                    description = "D-D"
                    currentChoiceIndex = 0
                }
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `verify items more than one`() {
        appConfig {
            config("DEV") {
                choice {
                    key = "D"
                    description = "D-D"
                    currentChoiceIndex = 0
                    item {
                        description = "E"
                    }
                }
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `verify same min & max`() {
        appConfig {
            config("DEV") {
                range {
                    key = "B"
                    description = "B-D"
                    min = 100
                    max = 100
                    currentValue = 50
                }
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `verify current value less than range`() {
        appConfig {
            config("DEV") {
                range {
                    key = "B"
                    description = "B-D"
                    min = 1
                    max = 100
                    currentValue = 0
                }
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `verify current value over than range`() {
        appConfig {
            config("DEV") {
                range {
                    key = "B"
                    description = "B-D"
                    min = 1
                    max = 100
                    currentValue = 101
                }
            }
        }
    }

    @Test
    fun `verify configuration repository`() {
        val configs = appConfig {
            config("DEV") {

                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = false
                }

                range {
                    key = "B"
                    description = "B-D"
                    min = 1
                    max = 100
                    currentValue = 50
                }

                editable {
                    key = "C"
                    description = "C-D"
                    currentValue = "C-V"
                }

                choice {
                    key = "D"
                    description = "D-D"
                    currentChoiceIndex = 0
                    item {
                        description = "E"
                    }
                    item {
                        description = "F"
                    }
                    item {
                        description = "G"
                    }
                }

            }
        }[0]
        val settings: Settings = mock()

        val repository = ConfigurationRepository(configs.configs, settings, "DEV")

        Assert.assertEquals(false, repository.getConfigBoolean("A"))
        Assert.assertEquals(50, repository.getConfigInt("B"))
        Assert.assertEquals("C-V", repository.getConfigString("C"))
        Assert.assertEquals("E", repository.getConfigPair("D").first)
        Assert.assertEquals(0, repository.getConfigPair("D").second)
    }


    @Test
    fun `verify immutable configuration repository`() {
        val configs = appConfig {
            config("DEV") {

                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = false
                }

                range {
                    key = "B"
                    description = "B-D"
                    min = 1
                    max = 100
                    currentValue = 50
                }

                editable {
                    key = "C"
                    description = "C-D"
                    currentValue = "C-V"
                }

                choice {
                    key = "D"
                    description = "D-D"
                    currentChoiceIndex = 0
                    item {
                        description = "E"
                    }
                    item {
                        description = "F"
                    }
                    item {
                        description = "G"
                    }
                }

            }
        }[0]

        val repository = ImmutableConfigurationRepository(configs.configs, "DEV")

        Assert.assertEquals(false, repository.getConfigBoolean("A"))
        Assert.assertEquals(50, repository.getConfigInt("B"))
        Assert.assertEquals("C-V", repository.getConfigString("C"))
        Assert.assertEquals("E", repository.getConfigPair("D").first)
        Assert.assertEquals(0, repository.getConfigPair("D").second)
    }

    @Test
    fun `verify same keys in different environment for immutable repository`() {
        val allConfig = appConfig {
            config("DEV") {
                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = false
                }
            }
            config("UAT") {
                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = true
                }
            }
        }
        val devConfig = allConfig[0]
        val uatConfig = allConfig[1]
        val repositoryDev = ImmutableConfigurationRepository(devConfig.configs, devConfig.environment)
        val repositoryUat = ImmutableConfigurationRepository(uatConfig.configs, uatConfig.environment)
        Assert.assertEquals(false, repositoryDev.getConfigBoolean("A"))
        Assert.assertEquals(true, repositoryUat.getConfigBoolean("A"))
    }


    @Test
    fun `verify same keys in different environment for mutable repository`() {
        val allConfig = appConfig {
            config("DEV") {
                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = false
                }
            }
            config("UAT") {
                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = true
                }
            }
        }
        val settings: Settings = mock()
        val devConfig = allConfig[0]
        val uatConfig = allConfig[1]
        val repositoryDev = ConfigurationRepository(devConfig.configs, settings, devConfig.environment)
        val repositoryUat = ConfigurationRepository(uatConfig.configs, settings, uatConfig.environment)
        Assert.assertEquals(false, repositoryDev.getConfigBoolean("A"))
        Assert.assertEquals(true, repositoryUat.getConfigBoolean("A"))
    }

    @Test(expected = IllegalStateException::class)
    fun `verify invalid type get`() {
        val configs = appConfig {
            config("DEV") {
                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = false
                }
            }
        }[0]

        val repository = ConfigurationRepository(configs.configs, mock(), "DEV")

        repository.getConfigInt("A")
    }


    @Test(expected = IllegalStateException::class)
    fun `verify invalid immutable get`() {
        val configs = appConfig {
            config("DEV") {
                switch {
                    key = "A"
                    description = "A-D"
                    switchValue = false
                }
            }
        }[0]

        val repository = ImmutableConfigurationRepository(configs.configs, "DEV")

        repository.getConfigInt("A")
    }
}
