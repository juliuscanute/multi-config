package com.juliuscanute.multiconfig.ui.host

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juliuscanute.multiconfig.base.MultiObserverEvent
import com.juliuscanute.multiconfig.ui.host.CommonState

class MainActivityViewModel : ViewModel() {
    private val commonState = MutableLiveData<MultiObserverEvent<CommonState>>()
    val commonActions: LiveData<MultiObserverEvent<CommonState>> = commonState

    fun selectConfiguration(environment: String) {
        commonState.postValue(
            MultiObserverEvent(
                CommonState.ButtonConfigurationState(
                    environment
                )
            )
        )
    }
}
