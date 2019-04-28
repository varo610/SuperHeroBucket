package com.adg.superherobucket.domain

import com.adg.superherobucket.data.Repository
import com.adg.superherobucket.domain.model.mapToDB
import com.adg.superherobucket.domain.model.toSuperHero
import com.adg.superherobucket.presentation.model.SuperHero
import com.adg.superherobucket.presentation.model.toDomainSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.rxkotlin.Maybes

class SearchSuperHeroUseCase constructor(
    private val repository: Repository
) {
    fun searchSuperHero(search: String): Maybe<List<SuperHero>> {

        return Maybes.zip(
            repository.searchSuperHeroes(search).toMaybe(),
            repository.getFavoriteSuperHeros()
        ) { searchResults, favs ->
            searchResults.map { result ->
                result.toSuperHero(favs.find { result.id == it.id }?.let { true } ?: kotlin.run { false })
            }

        }

    }

}

class GetFavoriteSuperHeros constructor(
    private val repository: Repository
) {
    fun getFavoriteSuperHeros(): Maybe<List<SuperHero>> {
        return repository.getFavoriteSuperHeros().map { list ->
            list.map { it.toSuperHero(true) }
        }
    }
}

class AddFavoriteSuperHero constructor(
    private val repository: Repository
) {
    fun addFavoriteSuperHero(superHero: SuperHero): Completable {
        return repository.addFavoriteSuperHero(superHero.toDomainSuperHero())
    }
}

class RemoveFavoriteSuperHero constructor(
    private val repository: Repository
) {
    fun removeFavoriteSuperHero(superHero: SuperHero): Completable {
        return repository.removeFavoriteSuperHero(superHero.toDomainSuperHero().mapToDB())
    }
}