package com.adg.superherobucket.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adg.superherobucket.presentation.model.BaseViewState

abstract class BaseViewModel<T> : ViewModel() {

    val viewState = MutableLiveData<BaseViewState<T>>()

    protected val state: T
        get() = viewState.value!!.data!!

    protected fun withState(block: (T) -> Unit) {
        block(state)
    }
}