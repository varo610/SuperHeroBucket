package com.adg.superherobucket.presentation.detail

import androidx.lifecycle.viewModelScope
import com.adg.superherobucket.domain.AddFavoriteSuperHero
import com.adg.superherobucket.domain.GetFavoriteSuperHeros
import com.adg.superherobucket.domain.RemoveFavoriteSuperHero
import com.adg.superherobucket.presentation.base.BaseViewModel
import com.adg.superherobucket.presentation.model.DetailViewState
import com.adg.superherobucket.presentation.model.SuperHero
import com.adg.superherobucket.presentation.utils.setSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel constructor(
    private val getFavoriteSuperHeros: GetFavoriteSuperHeros,
    private val addFavoriteSuperHero: AddFavoriteSuperHero,
    private val removeFavoriteSuperHero: RemoveFavoriteSuperHero
) : BaseViewModel<DetailViewState>() {

    fun setSuperHero(superHero: SuperHero) {
        viewModelScope.async {
            val fav = getFavoriteSuperHeros.isFav(superHero.id)
            viewState.setSuccess(DetailViewState(superHero.copy(favorite = fav)))
        }
    }

    fun favButtonOnClick() {
        withState {
            viewModelScope.launch {
                if (it.superHero.favorite) {
                    withContext(Dispatchers.IO) {
                        removeFavoriteSuperHero.removeFavoriteSuperHero(it.superHero)
                    }
                    viewState.setSuccess(DetailViewState(it.superHero.copy(favorite = false)))
                } else {
                    withContext(Dispatchers.IO) {
                        addFavoriteSuperHero.addFavoriteSuperHero(it.superHero)
                    }
                    viewState.setSuccess(DetailViewState(it.superHero.copy(favorite = true)))
                }
            }
        }

    }

}