package com.juliuscanute.multiconfig.ui

import android.content.Context
import com.juliuscanute.multiconfig.builder.appConfig
import com.juliuscanute.multiconfig.config.MultiConfig
import com.juliuscanute.multiconfig.config.startMultiConfig
import com.juliuscanute.multiconfig.model.ConfigurationRepository
import com.juliuscanute.multiconfig.model.ImmutableConfigurationRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Test

class MultiConfigTest {
    private val config = appConfig {
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
    }

    @Test
    fun `settings not initialized must return immutable configuration`() {
        startMultiConfig {
            multiConfig(configuration = config)
        }
        val repository = MultiConfig.getConfig()
        assert(repository is ImmutableConfigurationRepository) { "Repository must be immutable without settings" }
        Assert.assertEquals(50, repository.getConfigInt("B"))
    }

    @Test
    fun `when settings initialized must return mutable configuration`() {
        val context: Context = mock()
        whenever(context.getSharedPreferences(any(), any())).doReturn(mock())
        startMultiConfig(context = context) {
            multiConfig(configuration = config, controller = mock())
        }
        val repository = MultiConfig.getConfig()
        assert(repository is ConfigurationRepository) { "Repository must be mutable with settings" }
        Assert.assertEquals("C-V", repository.getConfigString("C"))
    }
}