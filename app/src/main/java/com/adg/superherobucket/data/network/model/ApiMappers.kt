package com.adg.superherobucket.data.network.model

import com.adg.superherobucket.domain.model.*

fun ApiSuperHero.mapToDomain(): DomainSuperHero{
    return DomainSuperHero(
        image = this.apiImage.mapToDomain(),
        appearance = this.apiAppearance.mapToDomain(),
        biography = this.apiBiography.mapToDomain(),
        id = this.id,
        name = this.name)
}

fun ApiImage.mapToDomain(): DomainImage{
    return DomainImage(
        url = this.url)
}

fun ApiAppearance.mapToDomain(): DomainAppearance{
    return DomainAppearance(
        eyeColor = this.eyeColor,
        gender = this.gender,
        race = this.race,
        weight = this.weight,
        height = this.height,
        hairColor = this.hairColor)
}

fun ApiBiography.mapToDomain(): DomainBiography{
    return DomainBiography(
        placeOfBirth = this.placeOfBirth,
        aliases = this.aliases,
        firstAppearance = this.firstAppearance,
        publisher = this.publisher,
        alignment = this.alignment,
        fullName = this.fullName,
        alterEgos = this.alterEgos)
}