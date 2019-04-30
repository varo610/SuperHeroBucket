package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.network.model.mapToDomain
import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Single

class NetworkDatasourceImp constructor(
    private val superHeroApiService: SuperHeroApiService
) : NetworkDatasource {
    override fun searchSuperHeroes(search: String): Single<List<DomainSuperHero>> {
        return superHeroApiService.superHeroSearch(search).map { response ->
            response.results.map{
                it.mapToDomain()
            }
        }
    }
}