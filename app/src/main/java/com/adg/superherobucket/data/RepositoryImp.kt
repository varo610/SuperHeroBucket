package com.adg.superherobucket.data

import com.adg.superherobucket.data.db.DBDatasource
import com.adg.superherobucket.data.network.NetworkDatasource
import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class RepositoryImp constructor(
    private val networkDatasource: NetworkDatasource,
    private val dbDatasource: DBDatasource
) : Repository{

    override fun searchSuperHeroes(search: String): Single<BaseEither<List<DomainSuperHero>>> {
        return networkDatasource.searchSuperHeroes(search)
    }

    override fun getFavoriteSuperHeros(): Maybe<List<DomainSuperHero>> {
        return dbDatasource.getAll()
    }

    override fun addFavoriteSuperHero(domainSuperHero: DomainSuperHero): Completable {
        return dbDatasource.addFavorite(domainSuperHero)
    }

    override fun removeFavoriteSuperHero(domainSuperHero: DomainSuperHero): Completable {
        return dbDatasource.removeFavorite(domainSuperHero)
    }


}