package com.juliuscanute.multiconfig.ui.host

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juliuscanute.multiconfig.base.MultiObserverEvent

class MainActivityViewModel : ViewModel() {
    private val commonState = MutableLiveData<MultiObserverEvent<CommonState>>()
    val commonActions: LiveData<MultiObserverEvent<CommonState>> = commonState
    lateinit var environment: String

    fun selectConfiguration(environment: String) {
        this.environment = environment
        commonState.postValue(
            MultiObserverEvent(
                CommonState.ButtonConfigurationState(
                    environment
                )
            )
        )
    }

    fun onTap() {
        commonState.postValue(
            MultiObserverEvent(
                CommonState.ButtonTapState(
                    environment
                )
            )
        )
    }
}
