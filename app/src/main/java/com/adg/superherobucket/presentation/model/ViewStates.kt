package com.adg.superherobucket.presentation.model

import com.adg.superherobucket.domain.model.DomainSuperHero

data class MainViewState(
    var superHeroList: List<SuperHero>)

data class DetailViewState(
    var domainSuperHeroList: List<DomainSuperHero>
)