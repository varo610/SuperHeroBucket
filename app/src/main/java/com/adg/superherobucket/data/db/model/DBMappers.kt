package com.adg.superherobucket.data.db.model

import com.adg.superherobucket.data.db.model.DBSuperHero
import com.adg.superherobucket.data.network.model.mapToDomain
import com.adg.superherobucket.domain.model.DomainAppearance
import com.adg.superherobucket.domain.model.DomainBiography
import com.adg.superherobucket.domain.model.DomainImage
import com.adg.superherobucket.domain.model.DomainSuperHero
import com.adg.superherobucket.presentation.model.Image

fun DBSuperHero.mapToDomain(): DomainSuperHero {

    return DomainSuperHero(
        image = DomainImage(url = this.url),
        appearance = DomainAppearance(
            eyeColor = this.eyeColor,
            gender = this.gender,
            race = this.race,
            weight = this.weight,
            height = this.height,
            hairColor = this.hairColor
        ),
        biography = DomainBiography(
            placeOfBirth = this.placeOfBirth,
            firstAppearance = this.firstAppearance,
            publisher = this.publisher,
            alignment = this.alignment,
            fullName = this.fullName,
            alterEgos = this.alterEgos
        ),
        id = this.id,
        name = this.name
    )

}