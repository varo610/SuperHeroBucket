package com.adg.superherobucket.presentation.detail

import androidx.lifecycle.viewModelScope
import com.adg.superherobucket.domain.AddFavoriteSuperHero
import com.adg.superherobucket.domain.RemoveFavoriteSuperHero
import com.adg.superherobucket.presentation.base.BaseViewModel
import com.adg.superherobucket.presentation.model.DetailViewState
import com.adg.superherobucket.presentation.model.SuperHero
import com.adg.superherobucket.presentation.utils.setSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        viewModelScope.launch {
            if (superHero.favorite) {

                withContext(Dispatchers.IO) { removeFavoriteSuperHero.removeFavoriteSuperHero(superHero) }
                superHero.favorite = false
                viewState.setSuccess(DetailViewState(superHero))

            } else {

                withContext(Dispatchers.IO) { addFavoriteSuperHero.addFavoriteSuperHero(superHero) }
                superHero.favorite = true
                viewState.setSuccess(DetailViewState(superHero))

            }
        }

    }

}