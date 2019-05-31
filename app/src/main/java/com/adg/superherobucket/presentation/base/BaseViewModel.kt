package com.adg.superherobucket.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adg.superherobucket.presentation.model.BaseViewState

abstract class BaseViewModel<T> : ViewModel(){

    val viewState = MutableLiveData<BaseViewState<T>>()

}