package com.adg.superherobucket.data.network.model

import com.google.gson.annotations.SerializedName

data class SuperHeroSearchResponse(
    @field:SerializedName("results-for") val resultsFor: String,
    @field:SerializedName("response") val response: String,
    @field:SerializedName("results") val results: List<ApiSuperHero>
)

