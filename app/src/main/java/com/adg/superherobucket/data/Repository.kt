package com.adg.superherobucket.data

import com.adg.superherobucket.data.network.NetworkDatasource
import com.adg.superherobucket.data.network.model.mapToDomain
import com.adg.superherobucket.domain.model.SuperHero
import io.reactivex.Single

class Repository constructor(
    private val networkDatasource: NetworkDatasource
) {

    fun searchSuperHeroes(search: String): Single<List<SuperHero>> {
        return networkDatasource.searchSuperHeroes(search)
            .map {apiSuperHeroList ->
                apiSuperHeroList.map {
                    it.mapToDomain()
                }
            }
    }


}