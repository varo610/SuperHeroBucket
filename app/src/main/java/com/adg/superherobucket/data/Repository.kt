package com.adg.superherobucket.data

import arrow.core.Either
import com.adg.superherobucket.common.BaseError
import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

typealias BaseEither<K> = Either<BaseError, K>

interface Repository {

    suspend fun searchSuperHeroes(search: String): BaseEither<List<DomainSuperHero>>

    suspend fun getFavoriteSuperHeros(): List<DomainSuperHero>

    suspend fun addFavoriteSuperHero(domainSuperHero: DomainSuperHero)

    suspend fun removeFavoriteSuperHero(domainSuperHero: DomainSuperHero)
}