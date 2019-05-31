package com.adg.superherobucket.data.db

import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe


interface DBDatasource {

    suspend fun getAll(): List<DomainSuperHero>

    suspend fun addFavorite(dbSuperHero: DomainSuperHero)

    suspend fun removeFavorite(dbSuperHero: DomainSuperHero)

}