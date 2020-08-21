package com.adg.superherobucket.data

import com.adg.superherobucket.data.db.DBDatasource
import com.adg.superherobucket.data.network.NetworkDatasource
import com.adg.superherobucket.domain.model.DomainSuperHero

class RepositoryImp constructor(
    private val networkDatasource: NetworkDatasource,
    private val dbDatasource: DBDatasource
) : Repository{

    override suspend fun searchSuperHeroes(search: String): BaseEither<List<DomainSuperHero>> {
        return networkDatasource.searchSuperHeroes(search)
    }

    override suspend fun getFavoriteSuperHeros(): List<DomainSuperHero> {
        return dbDatasource.getAll()
    }

    override suspend fun addFavoriteSuperHero(domainSuperHero: DomainSuperHero) {
        return dbDatasource.addFavorite(domainSuperHero)
    }

    override suspend fun removeFavoriteSuperHero(domainSuperHero: DomainSuperHero) {
        return dbDatasource.removeFavorite(domainSuperHero)
    }


}