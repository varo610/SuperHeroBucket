package com.adg.superherobucket.data

import arrow.core.Either
import com.adg.superherobucket.common.BaseError
import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

typealias BaseEither<K> = Either<BaseError, K>

interface Repository {

    fun searchSuperHeroes(search: String): Single<BaseEither<List<DomainSuperHero>>>

    fun getFavoriteSuperHeros(): Maybe<List<DomainSuperHero>>

    fun addFavoriteSuperHero(domainSuperHero: DomainSuperHero): Completable

    fun removeFavoriteSuperHero(domainSuperHero: DomainSuperHero): Completable
}