package com.adg.superherobucket.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiSuperHero(
    @field:SerializedName("image") val apiImage: ApiImage,
    @field:SerializedName("appearance") val apiAppearance: ApiAppearance,
    @field:SerializedName("work") val apiWork: ApiWork,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("powerstats") val apiPowerstats: ApiPowerstats,
    @field:SerializedName("id") val id: String,
    @field:SerializedName("biography") val apiBiography: ApiBiography,
    @field:SerializedName("connections") val apiConnections: ApiConnections
)

data class ApiImage(
    @field:SerializedName("url") val url: String
)

data class ApiAppearance(
    @field:SerializedName("eye-color") val eyeColor: String,
    @field:SerializedName("gender") val gender: String,
    @field:SerializedName("race") val race: String,
    @field:SerializedName("weight") val weight: List<String?>,
    @field:SerializedName("height") val height: List<String?>,
    @field:SerializedName("hair-color") val hairColor: String
)

data class ApiBiography(
    @field:SerializedName("place-of-birth") val placeOfBirth: String,
    @field:SerializedName("aliases") val aliases: List<String?>,
    @field:SerializedName("first-appearance") val firstAppearance: String,
    @field:SerializedName("publisher") val publisher: String,
    @field:SerializedName("alignment") val alignment: String,
    @field:SerializedName("full-name") val fullName: String,
    @field:SerializedName("alter-egos") val alterEgos: String
)

data class ApiConnections(
    @field:SerializedName("relatives") val relatives: String,
    @field:SerializedName("group-affiliation") val groupAffiliation: String
)

data class ApiPowerstats(
    @field:SerializedName("strength") val strength: String,
    @field:SerializedName("durability") val durability: String,
    @field:SerializedName("combat") val combat: String,
    @field:SerializedName("power") val power: String,
    @field:SerializedName("speed") val speed: String,
    @field:SerializedName("intelligence") val intelligence: String
)

data class ApiWork(
    @field:SerializedName("occupation") val occupation: String,
    @field:SerializedName("base") val base: String
)