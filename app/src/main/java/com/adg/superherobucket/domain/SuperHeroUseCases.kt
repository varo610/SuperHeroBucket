package com.adg.superherobucket.domain

import com.adg.superherobucket.data.Repository
import com.adg.superherobucket.domain.model.DomainSuperHero
import com.adg.superherobucket.domain.model.toSuperHero
import com.adg.superherobucket.presentation.model.SuperHero
import io.reactivex.Single

class SearchSuperHeroUseCase constructor(
    private val repository: Repository
) {
    fun searchSuperHero(search: String): Single<List<SuperHero>> {
        return repository.searchSuperHeroes(search).map { list ->
            list.map { it.toSuperHero() }
        }
    }
}