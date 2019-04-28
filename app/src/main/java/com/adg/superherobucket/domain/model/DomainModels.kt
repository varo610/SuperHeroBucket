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
    val weight: String,
    val height: String,
    val hairColor: String
)

data class DomainBiography(
    val placeOfBirth: String,
    val firstAppearance: String,
    val publisher: String,
    val alignment: String,
    val fullName: String,
    val alterEgos: String
)