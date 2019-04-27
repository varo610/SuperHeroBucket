package com.adg.superherobucket.domain.model

data class DomainSuperHero(
    val image: DomainImage,
    val appearance: DomainAppearance,
    val name: String,
    val id: String,
    val biography: DomainBiography
)

data class DomainImage(
    val url: String
)

data class DomainAppearance(
    val eyeColor: String,
    val gender: String,
    val race: String,
    val weight: List<String?>,
    val height: List<String?>,
    val hairColor: String
)

data class DomainBiography(
    val placeOfBirth: String,
    val aliases: List<String?>,
    val firstAppearance: String,
    val publisher: String,
    val alignment: String,
    val fullName: String,
    val alterEgos: String
)