package com.adg.superherobucket.data.db

import com.adg.superherobucket.data.db.model.DBSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe

class DBDatasourceImp
constructor(
    private val superHeroDAO: SuperHeroDAO
) : DBDatasource {

    override fun getAll(): Maybe<List<DBSuperHero>> {
        return superHeroDAO.getAll()
    }

    override fun addFavorite(dbSuperHero: DBSuperHero): Completable {
        return superHeroDAO.insert(dbSuperHero)
    }

    override fun removeFavorite(dbSuperHero: DBSuperHero): Completable {
        return superHeroDAO.delete(dbSuperHero)
    }

}