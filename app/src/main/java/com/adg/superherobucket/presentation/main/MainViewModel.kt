package com.adg.superherobucket.presentation.main

import com.adg.superherobucket.domain.SearchSuperHeroUseCase
import com.adg.superherobucket.presentation.base.BaseViewModel
import com.adg.superherobucket.presentation.model.BaseViewState
import com.adg.superherobucket.presentation.model.MainViewState
import com.adg.superherobucket.presentation.model.SuperHero
import com.adg.superherobucket.presentation.utils.setError
import com.adg.superherobucket.presentation.utils.setLoading
import com.adg.superherobucket.presentation.utils.setSuccess
import com.adg.superherobucket.utils.SingleLiveEvent
import io.reactivex.schedulers.Schedulers

class MainViewModel constructor(
    private val searchSuperHeroUseCase: SearchSuperHeroUseCase
) : BaseViewModel<MainViewState>() {

    val navigateToDetails = SingleLiveEvent<SuperHero>()

    fun search(text: String) {

        viewState.setLoading()

        compositeDisposable.add(
            searchSuperHeroUseCase.searchSuperHero(text)
                .subscribeOn(Schedulers.io())
                .subscribe { list ->
                    list.fold(
                        {
                            viewState.setError()
                        },
                        {
                            viewState.setSuccess(viewState.value?.data?.copy(superHeroList = it) ?: MainViewState(superHeroList = it))
                        })
                }

        )
    }


    fun superHeroOnClick(id: String) {
        val superHero = viewState.value?.data?.superHeroList?.first { it.id == id }
        navigateToDetails.postValue(superHero)
    }

}