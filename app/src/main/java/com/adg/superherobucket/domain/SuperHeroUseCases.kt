package com.adg.superherobucket.domain

import com.adg.superherobucket.data.Repository
import com.adg.superherobucket.domain.model.SuperHero
import io.reactivex.Single

class SearchSuperHeroUseCase constructor(
    private val repository: Repository
) {
    fun searchSuperHero(search: String): Single<List<SuperHero>> {
        return repository.searchSuperHeroes(search)
    }
}