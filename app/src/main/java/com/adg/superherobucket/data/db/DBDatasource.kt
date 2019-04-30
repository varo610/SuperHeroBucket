package com.adg.superherobucket.data.db

import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe


interface DBDatasource {

    fun getAll(): Maybe<List<DomainSuperHero>>

    fun addFavorite(dbSuperHero: DomainSuperHero): Completable

    fun removeFavorite(dbSuperHero: DomainSuperHero): Completable

}