package com.adg.superherobucket.data.db

import com.adg.superherobucket.data.db.model.mapToDomain
import com.adg.superherobucket.domain.model.DomainSuperHero
import com.adg.superherobucket.domain.model.mapToDB
import io.reactivex.Completable
import io.reactivex.Maybe

class DBDatasourceImp
constructor(
    private val superHeroDAO: SuperHeroDAO
) : DBDatasource {

    override fun getAll(): Maybe<List<DomainSuperHero>> {
        return superHeroDAO.getAll().map { list -> list.map { it.mapToDomain() } }
    }

    override fun addFavorite(dbSuperHero: DomainSuperHero): Completable {
        return superHeroDAO.insert(dbSuperHero.mapToDB())
    }

    override fun removeFavorite(dbSuperHero: DomainSuperHero): Completable {
        return superHeroDAO.delete(dbSuperHero.mapToDB())
    }

}