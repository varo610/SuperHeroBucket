package com.adg.superherobucket.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.adg.superherobucket.domain.SearchSuperHeroUseCase
import com.adg.superherobucket.presentation.model.MainViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel constructor(
    private val searchSuperHeroUseCase: SearchSuperHeroUseCase
) : ViewModel() {

    val mainViewState = MutableLiveData<MainViewState>()

    private val compositeDisposable = CompositeDisposable()

    fun search(text: String) {
        compositeDisposable.add(
            searchSuperHeroUseCase.searchSuperHero(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mainViewState.postValue(MainViewState(it))
                    }, {
                        //TODO fix
                        Log.e("TEMP", it.toString())
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}