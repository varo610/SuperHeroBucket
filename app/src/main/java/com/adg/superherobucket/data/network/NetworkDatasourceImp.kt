package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.BaseEither
import com.adg.superherobucket.data.network.model.mapToDomain
import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Single

class NetworkDatasourceImp constructor(
    private val superHeroApiService: SuperHeroApiService
) : NetworkDatasource {

    override suspend fun searchSuperHeroes(search: String): BaseEither<List<DomainSuperHero>> {

        return superHeroApiService.superHeroSearch(search).run().map {
            it.results.map { list ->
                list.mapToDomain()
            }
        }

    }

}