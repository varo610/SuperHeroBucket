package com.adg.superherobucket.presentation.utils

import androidx.lifecycle.MutableLiveData
import com.adg.superherobucket.presentation.model.BaseViewState
import com.adg.superherobucket.presentation.model.State.*

fun <T> MutableLiveData<BaseViewState<T>>.setSuccess(data: T) =
    postValue(BaseViewState(OK, data))

fun <T> MutableLiveData<BaseViewState<T>>.setError() =
    postValue(BaseViewState(ERROR, value?.data))

fun <T> MutableLiveData<BaseViewState<T>>.setLoading() =
    postValue(BaseViewState(LOADING, value?.data))