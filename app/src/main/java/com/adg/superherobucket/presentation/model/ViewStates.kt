package com.adg.superherobucket.presentation.model

data class MainViewState(
    var superHeroList: List<SuperHero>)

data class DetailViewState(
    var superHero: SuperHero
)