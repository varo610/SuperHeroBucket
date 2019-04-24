package com.adg.superherobucket.presentation.model

import com.adg.superherobucket.domain.model.SuperHero

data class MainViewState(
    var superHeroList: List<SuperHero>
)