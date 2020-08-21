package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.BaseEither
import com.adg.superherobucket.data.network.model.SuperHeroSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApiService {

    @GET("search/{search}")
    suspend fun superHeroSearch(@Path("search") search: String) : BaseEither<SuperHeroSearchResponse>

}