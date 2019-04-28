package com.adg.superherobucket.presentation

import android.util.Log
import com.adg.superherobucket.domain.AddFavoriteSuperHero
import com.adg.superherobucket.domain.RemoveFavoriteSuperHero
import com.adg.superherobucket.presentation.model.DetailViewState
import com.adg.superherobucket.presentation.model.SuperHero
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel constructor(
    private val addFavoriteSuperHero: AddFavoriteSuperHero,
    private val removeFavoriteSuperHero: RemoveFavoriteSuperHero
) : BaseViewModel<DetailViewState>() {

    private lateinit var superHero: SuperHero

    override fun onAttach() {
        //NA
    }

    fun setSuperHero(superHero: SuperHero){
        this.superHero = superHero
        viewState.postValue(DetailViewState(this.superHero))
    }

    fun favButtonOnClick() {

        if(superHero.favorite){
            compositeDisposable.add(
                removeFavoriteSuperHero.removeFavoriteSuperHero(superHero)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        superHero.favorite = false
                        viewState.postValue(DetailViewState(this.superHero))
                    }, {
                        Log.e("TEMP", it.toString())
                    })
            )
        }else {
            compositeDisposable.add(
                addFavoriteSuperHero.addFavoriteSuperHero(superHero)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        superHero.favorite = true
                        viewState.postValue(DetailViewState(this.superHero))
                    }, {
                        Log.e("TEMP", it.toString())
                    })
            )
        }


    }

}