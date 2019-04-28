package com.adg.superherobucket.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T> : ViewModel(){

    val viewState = MutableLiveData<T>()

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    abstract fun onAttach()

}