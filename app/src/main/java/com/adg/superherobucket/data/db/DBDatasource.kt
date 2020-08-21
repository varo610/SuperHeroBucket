package com.adg.superherobucket.data.db

import com.adg.superherobucket.domain.model.DomainSuperHero


interface DBDatasource {

    suspend fun getAll(): List<DomainSuperHero>

    suspend fun addFavorite(dbSuperHero: DomainSuperHero)

    suspend fun removeFavorite(dbSuperHero: DomainSuperHero)

}