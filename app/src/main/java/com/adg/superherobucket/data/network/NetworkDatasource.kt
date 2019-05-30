package com.adg.superherobucket.data.network

import arrow.core.Either
import com.adg.superherobucket.common.BaseError
import com.adg.superherobucket.data.BaseEither
import com.adg.superherobucket.domain.model.DomainSuperHero
import io.reactivex.Single

interface NetworkDatasource {

    fun searchSuperHeroes(search: String): Single<BaseEither<List<DomainSuperHero>>>

}