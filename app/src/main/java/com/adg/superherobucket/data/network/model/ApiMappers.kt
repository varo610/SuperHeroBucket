package com.adg.superherobucket.data.network.model

import com.adg.superherobucket.domain.model.*

fun ApiSuperHero.mapToDomain(): SuperHero{
    return SuperHero(
        image = this.apiImage.mapToDomain(),
        appearance = this.apiAppearance.mapToDomain(),
        biography = this.apiBiography.mapToDomain(),
        Connections = this.apiConnections.mapToDomain(),
        id = this.id,
        name = this.name,
        powerstats = this.apiPowerstats.mapToDomain(),
        Work = this.apiWork.mapToDomain())
}

fun ApiImage.mapToDomain(): Image{
    return Image(
        url = this.url)
}

fun ApiAppearance.mapToDomain(): Appearance{
    return Appearance(
        eyeColor = this.eyeColor,
        gender = this.gender,
        race = this.race,
        weight = this.weight,
        height = this.height,
        hairColor = this.hairColor)
}

fun ApiBiography.mapToDomain(): Biography{
    return Biography(
        placeOfBirth = this.placeOfBirth,
        aliases = this.aliases,
        firstAppearance = this.firstAppearance,
        publisher = this.publisher,
        alignment = this.alignment,
        fullName = this.fullName,
        alterEgos = this.alterEgos)
}

fun ApiConnections.mapToDomain(): Connections{
    return Connections(
        relatives = this.relatives,
        groupAffiliation = this.groupAffiliation )
}
fun ApiPowerstats.mapToDomain(): Powerstats{
    return Powerstats(
        strength = this.strength,
        durability = this.durability,
        combat = this.combat,
        power = this.power,
        speed = this.speed,
        intelligence = this.intelligence)
}
fun ApiWork.mapToDomain(): Work{
    return Work(
        occupation = this.occupation,
        base = this.base)
}