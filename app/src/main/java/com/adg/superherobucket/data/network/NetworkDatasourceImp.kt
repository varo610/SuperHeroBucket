package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.BaseEither
import com.adg.superherobucket.data.network.model.mapToDomain
import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Single

class NetworkDatasourceImp constructor(
    private val superHeroApiService: SuperHeroApiService
) : NetworkDatasource {

    override fun searchSuperHeroes(search: String): Single<BaseEither<List<DomainSuperHero>>> {

        return Single.just(superHeroApiService.superHeroSearch(search).run().map { it.results.map { list -> list.mapToDomain() } })

    }

}