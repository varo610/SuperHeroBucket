package com.adg.superherobucket.domain

import com.adg.superherobucket.data.BaseEither
import com.adg.superherobucket.data.Repository
import com.adg.superherobucket.domain.model.toSuperHero
import com.adg.superherobucket.presentation.model.SuperHero
import com.adg.superherobucket.presentation.model.toDomainSuperHero

class SearchSuperHeroUseCase constructor(
    private val repository: Repository
) {
    suspend fun searchSuperHero(search: String): BaseEither<List<SuperHero>> {

        val searchResults = repository.searchSuperHeroes(search)
        val favs = repository.getFavoriteSuperHeros()
        return searchResults.map { list ->
            list.map { result ->
                result.toSuperHero(favs.find { result.id == it.id }?.let { true } ?: kotlin.run { false })
            }
        }

    }

}

class GetFavoriteSuperHeros constructor(
    private val repository: Repository
) {
    suspend fun getFavoriteSuperHeros(): List<SuperHero> {
        return repository.getFavoriteSuperHeros().map { list ->
            list.toSuperHero(true)
        }
    }
}

class AddFavoriteSuperHero constructor(
    private val repository: Repository
) {
    suspend fun addFavoriteSuperHero(superHero: SuperHero) {
        return repository.addFavoriteSuperHero(superHero.toDomainSuperHero())
    }
}

class RemoveFavoriteSuperHero constructor(
    private val repository: Repository
) {
    suspend fun removeFavoriteSuperHero(superHero: SuperHero) {
        return repository.removeFavoriteSuperHero(superHero.toDomainSuperHero())
    }
}