package com.adg.superherobucket.domain.model

import com.adg.superherobucket.data.db.model.DBSuperHero
import com.adg.superherobucket.presentation.model.Appearance
import com.adg.superherobucket.presentation.model.Biography
import com.adg.superherobucket.presentation.model.Image
import com.adg.superherobucket.presentation.model.SuperHero

fun DomainSuperHero.toSuperHero(favorite: Boolean): SuperHero {
    return SuperHero(
        id = this.id,
        name = this.name,
        image = this.image.toImage(),
        biography = this.biography.toBiography(),
        appearance = this.appearance.toApperance(),
        favorite = favorite
    )
}

fun DomainImage.toImage(): Image {
    return Image(url = this.url)
}

fun DomainBiography.toBiography(): Biography {
    return Biography(
        placeOfBirth = this.placeOfBirth,
        firstAppearance = this.firstAppearance,
        publisher = this.publisher,
        alignment = this.alignment,
        fullName = this.fullName,
        alterEgos = this.alterEgos
    )
}

fun DomainAppearance.toApperance(): Appearance {
    return Appearance(
        eyeColor = this.eyeColor,
        gender = this.gender,
        race = this.race,
        weight = this.weight,
        height = this.height,
        hairColor = this.hairColor
    )
}

fun DomainSuperHero.mapToDB(): DBSuperHero {
    return DBSuperHero(
        url = this.image.url,
        eyeColor = this.appearance.eyeColor,
        gender = this.appearance.gender,
        race = this.appearance.race,
        weight = this.appearance.weight,
        height = this.appearance.height,
        hairColor = this.appearance.hairColor,
        placeOfBirth = this.biography.placeOfBirth,
        firstAppearance = this.biography.firstAppearance,
        publisher = this.biography.publisher,
        alignment = this.biography.alignment,
        fullName = this.biography.fullName,
        alterEgos = this.biography.alterEgos,
        id = this.id,
        name = this.name
    )
}