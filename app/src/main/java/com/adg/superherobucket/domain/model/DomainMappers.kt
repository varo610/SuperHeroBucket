package com.adg.superherobucket.domain.model

import com.adg.superherobucket.presentation.model.*

fun DomainSuperHero.toSuperHero(): SuperHero {
    return SuperHero(
        id = this.id,
        name = this.name,
        image = this.image.toImage(),
        biography = this.biography.toBiography(),
        appearance = this.appearance.toApperance()
    )
}

fun DomainImage.toImage(): Image {
    return Image(url = this.url)
}

fun DomainBiography.toBiography(): Biography {
    return Biography(
        placeOfBirth = this.placeOfBirth,
        aliases = this.aliases,
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

fun SuperHero.toDetail(): List<SuperHeroDetailItem> {
    return listOf(
        SuperHeroDetailItem(DetailType.SP_NAME, this.name),
        SuperHeroDetailItem(DetailType.SP_FULLNAME, this.biography.fullName),
        SuperHeroDetailItem(DetailType.SP_PLACE_OF_BIRTH, this.biography.placeOfBirth),
        SuperHeroDetailItem(DetailType.SP_GENDER, this.appearance.gender),
        SuperHeroDetailItem(
            DetailType.SP_WEIGHT,
            this.appearance.weight[1]?.let { it } ?: kotlin.run { "" }),
        SuperHeroDetailItem(
            DetailType.SP_HEIGHT,
            this.appearance.height[1]?.let { it } ?: kotlin.run { "" })
    )

}