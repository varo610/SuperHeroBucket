package com.adg.superherobucket.data

import com.adg.superherobucket.data.db.DBDatasource
import com.adg.superherobucket.data.db.model.DBSuperHero
import com.adg.superherobucket.data.db.model.mapToDomain
import com.adg.superherobucket.data.network.NetworkDatasource
import com.adg.superherobucket.data.network.model.mapToDomain
import com.adg.superherobucket.domain.model.DomainSuperHero
import com.adg.superherobucket.domain.model.mapToDB
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class Repository constructor(
    private val networkDatasource: NetworkDatasource,
    private val dbDatasource: DBDatasource
) {

    fun searchSuperHeroes(search: String): Single<List<DomainSuperHero>> {
        return networkDatasource.searchSuperHeroes(search)
            .map { apiSuperHeroList ->
                apiSuperHeroList.map {
                    it.mapToDomain()
                }
            }
    }

    fun getFavoriteSuperHeros(): Maybe<List<DomainSuperHero>> {
        return dbDatasource.getAll().map { dbSuperHeroList ->
            dbSuperHeroList.map {
                it.mapToDomain()
            }
        }
    }

    fun addFavoriteSuperHero(domainSuperHero: DomainSuperHero): Completable {
        return dbDatasource.addFavorite(domainSuperHero.mapToDB())
    }

    fun removeFavoriteSuperHero(dbSuperHero: DBSuperHero): Completable {
        return dbDatasource.removeFavorite(dbSuperHero)
    }


}