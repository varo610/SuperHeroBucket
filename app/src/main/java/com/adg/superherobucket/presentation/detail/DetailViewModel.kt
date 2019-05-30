package com.adg.superherobucket.presentation.detail

import com.adg.superherobucket.domain.AddFavoriteSuperHero
import com.adg.superherobucket.domain.RemoveFavoriteSuperHero
import com.adg.superherobucket.presentation.base.BaseViewModel
import com.adg.superherobucket.presentation.model.BaseViewState
import com.adg.superherobucket.presentation.model.DetailViewState
import com.adg.superherobucket.presentation.model.SuperHero
import com.adg.superherobucket.presentation.utils.setError
import com.adg.superherobucket.presentation.utils.setSuccess
import io.reactivex.schedulers.Schedulers

class DetailViewModel constructor(
    private val addFavoriteSuperHero: AddFavoriteSuperHero,
    private val removeFavoriteSuperHero: RemoveFavoriteSuperHero
) : BaseViewModel<DetailViewState>() {

    private lateinit var superHero: SuperHero

    fun setSuperHero(superHero: SuperHero) {
        this.superHero = superHero
        viewState.setSuccess(DetailViewState(this.superHero))
    }

    fun favButtonOnClick() {

        if (superHero.favorite) {
            compositeDisposable.add(
                removeFavoriteSuperHero.removeFavoriteSuperHero(superHero)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        superHero.favorite = false
                        viewState.setSuccess(DetailViewState(this.superHero))
                    }, {
                        viewState.setError()
                    })
            )
        } else {
            compositeDisposable.add(
                addFavoriteSuperHero.addFavoriteSuperHero(superHero)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        superHero.favorite = true
                        viewState.setSuccess(DetailViewState(this.superHero))
                    }, {
                        viewState.setError()
                    })
            )
        }

    }

}