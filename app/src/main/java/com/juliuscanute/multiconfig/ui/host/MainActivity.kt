/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.juliuscanute.multiconfig.ui.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.juliuscanute.multiconfig.MultiConfig
import com.juliuscanute.multiconfig.R
import com.juliuscanute.multiconfig.base.observeMultiEvent
import com.juliuscanute.multiconfig.databinding.ActivityMainBinding
import model.ConfigurationManager
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    private val configManager: ConfigurationManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.inflate<ActivityMainBinding>(layoutInflater, R.layout.activity_main, null, false)
        binding.model = mainActivityViewModel
        mainActivityViewModel.commonActions.observeMultiEvent(this) { state ->
            when (state) {
                is CommonState.ButtonConfigurationState -> {
                    binding.materialButton.text = getString(R.string.launch_with, state.environment)
                    binding.toolbar.title = getString(R.string.app_name_with, state.environment)
                }
                is CommonState.ButtonTapState -> {
                    MultiConfig.configuration = configManager.getConfiguration(state.environment)
                    try {
                        startActivity(MultiConfig.intent)
                        finish()
                    } catch (e: Exception) {
                        MaterialAlertDialogBuilder(this)
                            .setTitle("Error")
                            .setMessage("Start activity intent not set in Multiconfig.")
                            .setPositiveButton(android.R.string.ok, null).show()
                    }
                }
            }
        }

        setContentView(binding.root)
    }
}
