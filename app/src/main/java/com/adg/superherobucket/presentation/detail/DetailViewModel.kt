package com.adg.superherobucket.presentation.detail

import com.adg.superherobucket.domain.AddFavoriteSuperHero
import com.adg.superherobucket.domain.RemoveFavoriteSuperHero
import com.adg.superherobucket.presentation.base.BaseViewModel
import com.adg.superherobucket.presentation.model.DetailViewState
import com.adg.superherobucket.presentation.model.SuperHero
import io.reactivex.schedulers.Schedulers

class DetailViewModel constructor(
    private val addFavoriteSuperHero: AddFavoriteSuperHero,
    private val removeFavoriteSuperHero: RemoveFavoriteSuperHero
) : BaseViewModel<DetailViewState>() {

    private lateinit var superHero: SuperHero

    fun setSuperHero(superHero: SuperHero) {
        this.superHero = superHero
        viewState.postValue(DetailViewState(this.superHero))
    }

    fun favButtonOnClick() {

        if (superHero.favorite) {
            compositeDisposable.add(
                removeFavoriteSuperHero.removeFavoriteSuperHero(superHero)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        superHero.favorite = false
                        viewState.postValue(DetailViewState(this.superHero))
                    }, {
                        //TODO Error handling
                    })
            )
        } else {
            compositeDisposable.add(
                addFavoriteSuperHero.addFavoriteSuperHero(superHero)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        superHero.favorite = true
                        viewState.postValue(DetailViewState(this.superHero))
                    }, {
                        //TODO Error handling
                    })
            )
        }

    }

}