package com.adg.superherobucket.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adg.superherobucket.presentation.model.BaseViewState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T> : ViewModel(){

    val viewState = MutableLiveData<BaseViewState<T>>()

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}