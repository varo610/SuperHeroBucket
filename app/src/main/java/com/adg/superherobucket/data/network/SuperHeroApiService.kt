package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.network.model.SuperHeroSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroApiService {

    @GET("search/{search}")
    fun superHeroSearch(@Path("search") search: String) : CustomCall<SuperHeroSearchResponse>

}