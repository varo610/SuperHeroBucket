package com.adg.superherobucket.domain.model

data class SuperHero(
    val image: Image,
    val Appearance: Appearance,
    val Work: Work,
    val name: String,
    val Powerstats: Powerstats,
    val id: String,
    val Biography: Biography,
    val Connections: Connections
)

data class Image(
    val url: String
)

data class Appearance(
    val eyeColor: String,
    val gender: String,
    val race: String,
    val weight: List<String?>,
    val height: List<String?>,
    val hairColor: String
)

data class Biography(
    val placeOfBirth: String,
    val aliases: List<String?>,
    val firstAppearance: String,
    val publisher: String,
    val alignment: String,
    val fullName: String,
    val alterEgos: String
)

data class Connections(
    val relatives: String,
    val groupAffiliation: String
)

data class Powerstats(
    val strength: String,
    val durability: String,
    val combat: String,
    val power: String,
    val speed: String,
    val intelligence: String
)

data class Work(
    val occupation: String,
    val base: String
)