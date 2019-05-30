package com.adg.superherobucket.presentation.model

sealed class State {
    object LOADING : State()
    object OK : State()
    object ERROR : State()
}

data class BaseViewState<T>(
    var state: State = State.LOADING,
    var data: T?
)

data class MainViewState(
    var superHeroList: List<SuperHero> = listOf()
)

data class DetailViewState(
    var superHero: SuperHero
)