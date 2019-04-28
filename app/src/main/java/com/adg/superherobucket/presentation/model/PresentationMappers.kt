package com.adg.superherobucket.presentation.model

import com.adg.superherobucket.domain.model.DomainAppearance
import com.adg.superherobucket.domain.model.DomainBiography
import com.adg.superherobucket.domain.model.DomainImage
import com.adg.superherobucket.domain.model.DomainSuperHero


fun SuperHero.toDetail(): List<SuperHeroDetailItem> {
    return listOf(
        SuperHeroDetailItem(DetailType.SP_NAME, this.name),
        SuperHeroDetailItem(DetailType.SP_FULLNAME, this.biography.fullName),
        SuperHeroDetailItem(DetailType.SP_PLACE_OF_BIRTH, this.biography.placeOfBirth),
        SuperHeroDetailItem(DetailType.SP_GENDER, this.appearance.gender),
        SuperHeroDetailItem(
            DetailType.SP_WEIGHT,
            this.appearance.weight),
        SuperHeroDetailItem(
            DetailType.SP_HEIGHT,
            this.appearance.height)
    )

}

fun SuperHero.toDomainSuperHero(): DomainSuperHero {
    return DomainSuperHero(
        id = this.id,
        name = this.name,
        image = this.image.toDomainImage(),
        biography = this.biography.toDomainBiography(),
        appearance = this.appearance.toDomainApperance()
    )
}

fun Image.toDomainImage(): DomainImage {
    return DomainImage(url = this.url)
}

fun Biography.toDomainBiography(): DomainBiography {
    return DomainBiography(
        placeOfBirth = this.placeOfBirth,
        firstAppearance = this.firstAppearance,
        publisher = this.publisher,
        alignment = this.alignment,
        fullName = this.fullName,
        alterEgos = this.alterEgos
    )
}

fun Appearance.toDomainApperance(): DomainAppearance {
    return DomainAppearance(
        eyeColor = this.eyeColor,
        gender = this.gender,
        race = this.race,
        weight = this.weight,
        height = this.height,
        hairColor = this.hairColor
    )
}