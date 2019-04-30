package com.adg.superherobucket.data.network

import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Single

interface NetworkDatasource {

    fun searchSuperHeroes(search: String): Single<List<DomainSuperHero>>

}