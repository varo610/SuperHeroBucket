package com.adg.superherobucket.data.db

import com.adg.superherobucket.data.db.model.mapToDomain
import com.adg.superherobucket.domain.model.DomainSuperHero
import com.adg.superherobucket.domain.model.mapToDB

class DBDatasourceImp
constructor(
    private val superHeroDAO: SuperHeroDAO
) : DBDatasource {

    override suspend fun getAll(): List<DomainSuperHero> {
        return superHeroDAO.getAll().map { it.mapToDomain() }
    }

    override suspend fun addFavorite(dbSuperHero: DomainSuperHero) {
        return superHeroDAO.insert(dbSuperHero.mapToDB())
    }

    override suspend fun removeFavorite(dbSuperHero: DomainSuperHero) {
        return superHeroDAO.delete(dbSuperHero.mapToDB())
    }

}