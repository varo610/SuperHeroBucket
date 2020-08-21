package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.BaseEither
import com.adg.superherobucket.data.network.model.mapToDomain
import com.adg.superherobucket.domain.model.DomainSuperHero

class NetworkDatasourceImp constructor(
    private val superHeroApiService: SuperHeroApiService
) : NetworkDatasource {

    override suspend fun searchSuperHeroes(search: String): BaseEither<List<DomainSuperHero>> =
        superHeroApiService.superHeroSearch(search)
            .map { response -> response.results.map { it.mapToDomain() } }

}
