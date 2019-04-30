package com.adg.superherobucket.data

import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface Repository {

    fun searchSuperHeroes(search: String): Single<List<DomainSuperHero>>

    fun getFavoriteSuperHeros(): Maybe<List<DomainSuperHero>>

    fun addFavoriteSuperHero(domainSuperHero: DomainSuperHero): Completable

    fun removeFavoriteSuperHero(domainSuperHero: DomainSuperHero): Completable
}