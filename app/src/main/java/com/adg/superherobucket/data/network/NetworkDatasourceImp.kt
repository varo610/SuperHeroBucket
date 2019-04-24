package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.network.model.ApiSuperHero
import io.reactivex.Single

class NetworkDatasourceImp constructor(
    private val superHeroApiService: SuperHeroApiService
) : NetworkDatasource {
    override fun searchSuperHeroes(search: String): Single<List<ApiSuperHero>> {
        return superHeroApiService.superHeroSearch(search).map {
            it.results
        }
    }
}