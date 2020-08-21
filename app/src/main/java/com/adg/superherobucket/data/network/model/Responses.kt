package com.adg.superherobucket.data.network.model

import com.google.gson.annotations.SerializedName


const val DEFAULT_RESPONSE_RESULT_ERROR = "error"
const val DEFAULT_RESPONSE_RESULT_SUCESS = "success"
const val UNAUTHORIZED_RESPONSE = "access denied"

val DEFAULT_RESPONSE_ERROR: String? = null

open class BaseResponse(
   @field:SerializedName("response")
    val response: String = DEFAULT_RESPONSE_RESULT_ERROR,
   @field:SerializedName("error")
    val error: String? = DEFAULT_RESPONSE_ERROR
)


data class SuperHeroSearchResponse(
   @field:SerializedName("results-for")
    val resultsFor: String,
   @field:SerializedName("results")
    val results: List<ApiSuperHero>
): BaseResponse()
