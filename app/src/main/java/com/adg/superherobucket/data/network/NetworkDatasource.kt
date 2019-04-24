package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.network.model.ApiSuperHero
import io.reactivex.Single

interface NetworkDatasource {

    fun searchSuperHeroes(search: String): Single<List<ApiSuperHero>>

}