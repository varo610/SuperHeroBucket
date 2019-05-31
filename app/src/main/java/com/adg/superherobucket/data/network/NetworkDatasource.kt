package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.BaseEither
import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Single

interface NetworkDatasource {

    suspend fun searchSuperHeroes(search: String): BaseEither<List<DomainSuperHero>>

}