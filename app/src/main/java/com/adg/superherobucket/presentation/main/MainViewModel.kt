package com.adg.superherobucket.presentation.main

import android.util.Log
import com.adg.superherobucket.domain.SearchSuperHeroUseCase
import com.adg.superherobucket.presentation.base.BaseViewModel
import com.adg.superherobucket.presentation.model.MainViewState
import com.adg.superherobucket.presentation.model.SuperHero
import com.adg.superherobucket.utils.SingleLiveEvent
import io.reactivex.schedulers.Schedulers

class MainViewModel constructor(
    private val searchSuperHeroUseCase: SearchSuperHeroUseCase
) : BaseViewModel<MainViewState>() {

    val navigateToDetails = SingleLiveEvent<SuperHero>()

    fun search(text: String) =
        compositeDisposable.add(
            searchSuperHeroUseCase.searchSuperHero(text)
                .subscribeOn(Schedulers.io())
                .subscribe { list ->
                    list.fold(
                        {
                            //TODO Show error
                            Log.d("", "")
                        },
                        {
                            viewState.postValue(MainViewState(it))
                        })
                }

        )


    fun superHeroOnClick(id: String) {
        val superHero = viewState.value?.superHeroList?.first { it.id == id }
        navigateToDetails.postValue(superHero)
    }

}